package minesweeper.domain.state

import minesweeper.domain.Board
import minesweeper.domain.Position

interface State {

    val board: Board

    fun isFinished(): Boolean

    fun open(position: Position): State
}
