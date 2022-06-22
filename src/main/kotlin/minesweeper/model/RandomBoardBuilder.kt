package minesweeper.model

import minesweeper.model.board.RandomMineBoard
import minesweeper.model.board.coordinate.BoardArea

class RandomBoardBuilder(private val boardArea: BoardArea, mineCount: Int) : BoardBuilder {

    private val mineCount: Int = mineCount.coerceIn(1, boardArea.cellCount)

    override fun createNewBoard() = RandomMineBoard(boardArea, mineCount)
}
