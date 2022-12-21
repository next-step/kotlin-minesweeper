package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test


internal class CoordinateTest {
    @Test
    fun `좌표는 x와 y값을 가진다`() {
        val coordinate = Coordinate(10, 10)

        coordinate.x shouldBe 10
        coordinate.y shouldBe 10
    }
}
