package minesweeper.domain.state

import minesweeper.domain.Board
import minesweeper.domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        if (board.isMine(position)) {
            val openBoard = board.allOpen()
            return Lose(openBoard)
        }
        val newBoard = board.open(position)
        return if (newBoard.isAllOpen()) {
            Win(newBoard)
        } else {
            Running(newBoard)
        }
    }

    override fun isFinished(): Boolean = false
}
