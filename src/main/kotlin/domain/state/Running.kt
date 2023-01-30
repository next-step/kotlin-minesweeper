package domain.state

import domain.Board
import domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        val result = board.open(position)
        if (result.isMine(position)) {
            return Finished.Lose(result)
        }

        return if (result.isAllOpen()) {
            Finished.Win(result)
        } else {
            Running(result)
        }
    }

    override fun isFinished(): Boolean = false
}
