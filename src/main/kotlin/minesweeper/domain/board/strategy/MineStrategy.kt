package minesweeper.domain.board.strategy

import minesweeper.domain.common.NumberSet

interface MineStrategy {

    fun getMineIndices(numberOfCells: Int, numberOfMines: Int): NumberSet
}
