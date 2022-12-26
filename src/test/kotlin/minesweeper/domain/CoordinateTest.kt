package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CoordinateTest {
    @Test
    fun `좌표는 rows와 cols값을 가진다`() {
        val coordinate = Coordinate(10, 10)

        coordinate.rows shouldBe 10
        coordinate.cols shouldBe 10
    }
}
