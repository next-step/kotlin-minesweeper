package minesweeper.state

import minesweeper.model.Board
import minesweeper.model.Position

data class Running(override val board: Board) : State {

    override fun tryOpen(position: Position): State {
        if (board.isMine(position)) {
            return Finished.Lose(board)
        }
        val board = board.tryOpen(position)
        return if (board.isAllOpened()) {
            Finished.Win(board)
        } else {
            Running(board)
        }
    }

    override fun isFinished(): Boolean = false
}
