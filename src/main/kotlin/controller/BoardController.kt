package controller

import domain.Board
import domain.BoardFactory
import domain.Height
import domain.MinesPosition
import domain.Width

class BoardController {
    fun create(
        width: Width,
        height: Height,
        mineCount: Int,
    ): Board {
        val minesPosition = MinesPosition.generate(
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
