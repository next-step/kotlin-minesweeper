package minesweeper.state

import minesweeper.model.Board
import minesweeper.model.Position

sealed class Finished(override val board: Board) : State {

    final override fun isFinished(): Boolean = true

    final override fun tryOpen(position: Position): State = this

    data class Win(override val board: Board) : Finished(board)

    data class Lose(override val board: Board) : Finished(board)
}
