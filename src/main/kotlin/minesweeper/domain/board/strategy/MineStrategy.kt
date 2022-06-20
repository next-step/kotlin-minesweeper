package minesweeper.domain.board.strategy

import minesweeper.domain.common.PositiveInt

interface MineStrategy {

    fun strategy(): (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int>
}
