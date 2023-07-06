package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.BoardGenerator
import minesweeper.domain.PositiveInt

class BoardSetting(
    val height: PositiveInt,
    val width: PositiveInt,
    val mineCount: PositiveInt,
) {
    fun toBoard(): Board {
        return BoardGenerator(
            height = height,
            width = width,
            mineCount = mineCount,
        ).generate()
    }
}
