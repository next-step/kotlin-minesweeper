package controller

import domain.Board
import domain.BoardFactory
import domain.Height
import domain.Width
import util.MinePositionsGenerator

class BoardController {
    fun create(
        width: Width,
        height: Height,
        mineCount: Int,
    ): Board {
        val minesPosition = MinePositionsGenerator.generate(
            maxWidth = width,
            maxHeight = height,
            generateCount = mineCount
        )

        return BoardFactory.create(
            width,
            height,
            minesPosition,
        )
    }
}
