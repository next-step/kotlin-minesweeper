package minesweeper.domain.board

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardRangeTest {

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "-1, 1",
    )
    fun `보드 범위의 높이가 0 이하면 IllegalArgumentException 이 발생`(height: Int, width: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardRange(height = height, width = width)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "1, 0",
        "1, -1",
    )
    fun `보드 범위의 너비가 0 이하면 IllegalArgumentException 이 발생`(height: Int, width: Int) {
        shouldThrow<IllegalArgumentException> {
            BoardRange(height = height, width = width)
        }
    }
}
