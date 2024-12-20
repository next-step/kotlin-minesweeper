package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell

interface OpenCellStrategy {
    fun open(
        originalCells: Cells,
        targetCell: Cell,
    ): Cells
}
