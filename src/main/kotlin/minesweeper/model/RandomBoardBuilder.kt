package minesweeper.model

import minesweeper.model.board.Board
import minesweeper.model.board.coordinate.BoardArea

class RandomBoardBuilder(private val boardArea: BoardArea, mineCount: Int) : BoardBuilder {

    private val mineCount: Int = mineCount.coerceIn(1, boardArea.cellCount)

    override fun createNewBoard(): Board {
        val minePositions = boardArea.shuffled().take(mineCount)
        return Board.build(boardArea) { position -> position in minePositions }
    }
}
