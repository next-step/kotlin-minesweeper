package minesweeper.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MinesTest {
    @Test
    internal fun `지뢰가 존재하는지 판단한다`() {
        val checkTarget1 = Cell(1, 1)
        val checkTarget2 = Cell(2, 2)
        val mines = Mines(setOf(Cell(1, 1)))
        assertThat(mines.contains(checkTarget1)).isTrue
        assertThat(mines.contains(checkTarget2)).isFalse
    }

    @Test
    internal fun `지뢰는 중복될 수 없다`() {
        val cell = Cell(1, 1)
        val mines = Mines(setOf(cell, cell, cell))
        assertThat(mines.size).isEqualTo(1)
    }
}
