package tdd.minesweeper.domain.strategy

import tdd.minesweeper.domain.Area
import tdd.minesweeper.domain.Cells
import tdd.minesweeper.domain.Location

interface BoardCellsCreator {
    fun createCells(
        area: Area,
        countOfMines: Int,
        inputManualMineLocations: Set<Location>,
    ): Cells
}
