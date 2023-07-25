package minesweeper.domain.field

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PositionTest {
    @ParameterizedTest
    @CsvSource("-1, 0", "0, -1", "-1, -1")
    fun `x 또는 y가 0 미만이면 예외가 발생한다`(x: Int, y: Int) {
        shouldThrow<IllegalArgumentException> {
            Position(x, y)
        }
    }
}
