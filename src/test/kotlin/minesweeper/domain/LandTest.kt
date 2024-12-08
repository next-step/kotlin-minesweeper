package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class LandTest {

    @Test
    fun `지뢰가 없는 경우 인접 지뢰 개수는 0이다`() {
        val land = Land()
        land.updateAdjacentMines(
            listOf(
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
            ),
        )

        land.adjacentMines shouldBe 0
    }

    @Test
    fun `인접 지뢰가 1개인 경우 인접 지뢰 개수는 1이다`() {
        val land = Land()
        land.updateAdjacentMines(
            listOf(
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Land(),
                Mine(),
            ),
        )

        land.adjacentMines shouldBe 1
    }
}
