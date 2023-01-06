package domain.state

import domain.Board
import domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        val result = board.open(position)
        if (result.isAllOpen()) {
            return Finished.Boom(result)
        }
        return if (result.isClear()) {
            Finished.Clear(result)
        } else {
            Running(result)
        }
    }

    override fun isFinished(): Boolean = false
}
