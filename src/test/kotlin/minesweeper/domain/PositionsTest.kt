package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PositionsTest {

    @Test
    fun `지뢰가_함께있는_게임판을_생성한다`() {
        val positions = Positions(hashSetOf(Position(3, 0), Position(1, 1)))
        val grounds = mutableListOf(listOf("C ", "C ", "C ", "C "), listOf("C ", "C ", "C ", "C "))

        val groundWithMines = positions.generateGroundWithMine(grounds)

        assertThat(groundWithMines[0][3]).isEqualTo("* ")
        assertThat(groundWithMines[1][1]).isEqualTo("* ")
    }
}
