package minesweeper.domain.position

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "-1, 1"
    )
    fun `열(x) 값이 1 미만이면 IllegalArgumentException 이 발생한다`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x = x, y = y)
        }
    }

    @ParameterizedTest
    @CsvSource(
        "0, 1",
        "-1, 1"
    )
    fun `행(y) 값이 1 미만이면 IllegalArgumentException 이 발생한다`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x = x, y = y)
        }
    }
}
