package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PositionTest {
    @ParameterizedTest
    @CsvSource(
        "0, 0",
        "3, 1",
    )
    internal fun `x, y 좌표를 가진다`(expectedX: Int, expectedY: Int) {
        val sut = Position(x = expectedX, y = expectedY)
        sut.x shouldBe expectedX
        sut.y shouldBe expectedY
    }

    @ParameterizedTest
    @CsvSource(
        "-1, -1",
        "0, -1",
        "-1, 0"
    )
    internal fun `좌표가 0보다 작으면 예외가 발생한다`(valueX: Int, valueY: Int) {
        assertThrows<IllegalArgumentException> { Position(x = valueX, y = valueY) }
    }
}
