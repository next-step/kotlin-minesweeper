package minesweeper.domain

import io.kotest.matchers.shouldBe
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
}
