package minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MotherCellTest {
    @Test
    internal fun `2 x 2 좌상단 코너`() {
        val bombCell = MotherCell(bomb = true)
        val cells = listOf(bombCell, MotherCell(), MotherCell(), MotherCell())
        for ((index, cell) in cells.withIndex()) {
            cell.sideCells = Matrix(2, 2).around(index).map { cells[it] }
        }
        assertThat(bombCell.sideCells).contains(cells[1], cells[2], cells[3])
    }

    @Test
    internal fun `주변셀의 숫자를 증가한다`() {
        val bombCell = MotherCell(bomb = true).apply {
            sideCells = listOf(MotherCell(), MotherCell(), MotherCell())
        }

        bombCell.increaseCount()

        assertThat(bombCell.sideCells).allSatisfy {
            assertThat(it.count).isEqualTo(1)
        }
    }
}
