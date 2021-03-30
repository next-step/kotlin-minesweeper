package domain.coordinategenerator

import domain.Coordinate
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class BoardCoordinatesGeneratorTest {

    @Test
    fun `(1,1) 좌표부터 입력한 길이만큼 모든 좌표를 생성한다`() {
        val (width, height) = 3 to 2
        val result = BoardCoordinatesGenerator(width = width, height = height).generate()
        assertThat(result).containsExactlyInAnyOrder(
            Coordinate(1, 1),
            Coordinate(1, 2),
            Coordinate(2, 1),
            Coordinate(2, 2),
            Coordinate(3, 1),
            Coordinate(3, 2)
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "1, -1"
    )
    fun `보드 좌표들을 생성 시 높이나 너비가 0보다 크지 않은 경우 예외를 반환한다`(width: Int, height: Int) {
        val expectedMessage = "너비와 높이는 0보다 커야 합니다. width: $width, height: $height"
        val result = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            BoardCoordinatesGenerator(width = width, height = height)
        }
        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
