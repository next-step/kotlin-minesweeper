package minesweeper.state

import minesweeper.model.Board
import minesweeper.model.Position

data class Running(override val board: Board) : State {

    override fun tryOpen(position: Position): State {
        TODO("Not yet implemented")
    }

    override fun isFinished(): Boolean = false
}
