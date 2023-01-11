package domain.state

import domain.Board
import domain.Position

interface State {
    val board: Board

    fun open(position: Position): State
    fun isAllOpen(): Boolean = board.isAllOpen()
    fun isFinished(): Boolean
}
