package minesweeper.domain.strategy

import minesweeper.domain.cell.Cell
import minesweeper.domain.cell.ClosedCell
import minesweeper.domain.cell.NumberOfAdjacentMines

class OpenCellStrategyHandler {
    fun findStrategy(targetCell: Cell): OpenCellStrategy =
        when {
            targetCell !is ClosedCell -> NoChangeOpenCellStrategy()
            targetCell.hasLandmine -> SingleOpenCellStrategy()
            targetCell.numberOfAdjacentLandmines > NumberOfAdjacentMines.ZERO -> SingleOpenCellStrategy()
            else -> CascadingOpenCellStrategy()
        }
}
