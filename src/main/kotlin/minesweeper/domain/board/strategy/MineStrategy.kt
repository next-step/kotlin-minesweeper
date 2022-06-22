package minesweeper.domain.board.strategy

import minesweeper.domain.common.NumberSet
import minesweeper.domain.common.PositiveInt

interface MineStrategy {

    fun getMineIndices(numberOfCells: PositiveInt, numberOfMines: PositiveInt): NumberSet
}
