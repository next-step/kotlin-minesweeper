package domain.state

import domain.Board
import domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        if (board.getBlockByPosition(position)!!.isMine()) {
            return Finished.Lose(board)
        }
        val result = board.open(position)
        return if (result.isAllOpen()) {
            Finished.Win(result)
        } else {
            Running(result)
        }
    }

    override fun isFinished(): Boolean = false
}
