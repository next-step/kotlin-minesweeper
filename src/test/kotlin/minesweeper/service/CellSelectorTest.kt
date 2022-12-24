package minesweeper.service

import minesweeper.model.Cell
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CellSelectorTest {

    @Test
    internal fun `특정 셀의 주변 8방향 셀을 구한다`() {
        val target = Cell(1, 0)
        val expectedCells = arrayOf(
            Cell(0, -1), Cell(1, -1), Cell(2, -1),
            Cell(0, 0), Cell(2, 0),
            Cell(0, 1), Cell(1, 1), Cell(2, 1),
        )

        val actualCells = CellSelector.getSurroundingCells(target)

        assertThat(actualCells).containsExactlyInAnyOrder(*expectedCells)
    }
}
