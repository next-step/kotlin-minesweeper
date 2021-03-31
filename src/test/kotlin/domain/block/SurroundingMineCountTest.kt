package domain.block

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class SurroundingMineCountTest {

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 8])
    fun `주변 지뢰의 개수가 0~8 사이 값으로 생성할 수 있다`(input: Int) {
        val result = SurroundingMineCount(input)
        assertThat(result).isNotNull
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 9])
    fun `주변 지뢰의 개수가 0~8 사이의 값이 아닌 경우 예외를 반환한다`(input: Int) {
        val expected = "주변 지뢰의 개수는 0~8 사이의 값이어야 합니다. value: $input"
        val result = org.junit.jupiter.api.assertThrows<IllegalArgumentException> { SurroundingMineCount(input) }
        assertThat(result.message).isEqualTo(expected)
    }
}
