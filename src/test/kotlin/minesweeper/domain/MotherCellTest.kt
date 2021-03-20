package minesweeper.domain

import minesweeper.domain.CoordinateTest.Coordinate
import minesweeper.domain.CoordinateTest.Matrix
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MotherCellTest {
    @Test
    internal fun `2 x 2 좌상단 코너`() {
        val bombCell = MotherCell(bomb = true)
        val cells = listOf(bombCell, MotherCell(), MotherCell(), MotherCell())
        for ((index, cell) in cells.withIndex()) {
            cell.sideCells = Coordinate(index, Matrix(2, 2)).sideIndexes.map { cells[it] }
        }
        assertThat(bombCell.sideCells).contains(cells[1], cells[2], cells[3])
    }

    class MotherCell(bomb: Boolean = false) {
        var sideCells = emptyList<MotherCell>()
    }
}
