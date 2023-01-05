package domain.state

import domain.Board
import domain.Position

class Running(override val board: Board) : State {
    override fun open(position: Position): State {
        val result = board.open(position)
        if (result.isClear() || result.isAllOpen()) {
            return Finished(result)
        }
        return Running(result)
    }
}
