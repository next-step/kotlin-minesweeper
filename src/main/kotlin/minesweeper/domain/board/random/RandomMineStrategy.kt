package minesweeper.domain.board.random

import minesweeper.domain.common.PositiveInt

interface RandomMineStrategy {

    fun strategy(): (numberOfCells: PositiveInt, numberOfMines: PositiveInt) -> List<Int> // TODO - 패키지명, 클래스명 변경
}
