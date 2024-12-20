package minesweeper.domain.strategy

import minesweeper.domain.Cells
import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell

class SingleOpenCellStrategy : OpenCellStrategy {
    override fun open(
        originalCells: Cells,
        targetCell: Cell,
    ): Cells = Cells(originalCells.map { if (it == targetCell && it is ClosedCell) it.open() else it })
}
