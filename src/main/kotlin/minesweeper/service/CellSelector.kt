package minesweeper.service

import minesweeper.model.MineMap
import minesweeper.model.Mines
import minesweeper.model.getDefaultCellPool

private const val PREV_INDEX = -1
private const val NEXT_INDEX = 1
private const val HERE_INDEX = 0

object CellSelector {
    fun selectRandomCells(mineMap: MineMap, cellCount: Int): Mines {
        val shuffledMines = getDefaultCellPool(mineMap.rowSize, mineMap.columnSize)
            .keys
            .shuffled()
            .take(cellCount)
            .toSet()
        return Mines(shuffledMines)
    }
}
