package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MineBoardFactoryTest {

    @Test
    fun `지뢰찾기판을 생성할 수 있다`() {
        val result = MineBoardFactory.create(10, 10)
        assertThat(result).isNotNull
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "1, -1"
    )
    fun `지뢰찾기판을 생성 시 높이나 너비가 0보다 크지 않은 경우 예외를 반환한다`(width: Int, height: Int) {
        val expectedMessage = "너비와 높이는 0보다 커야 합니다. width: $width, height: $height"
        val result = assertThrows<IllegalArgumentException> { MineBoardFactory.create(width = width, height = height) }
        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
