package minesweeper.state

import minesweeper.model.Board
import minesweeper.model.Position

interface State {

    val board: Board

    fun tryOpen(position: Position): State

    fun isFinished(): Boolean
}
