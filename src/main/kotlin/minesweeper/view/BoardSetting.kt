package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.PositiveInt

class BoardSetting(
    val height: PositiveInt,
    val width: PositiveInt,
    val mineCount: PositiveInt,
) {
    fun toBoard(): Board {
        return Board.of(
            height = height,
            width = width,
            mineCount = mineCount,
        )
    }
}
