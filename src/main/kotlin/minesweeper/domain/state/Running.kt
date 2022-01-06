package minesweeper.domain.state

import minesweeper.domain.Board
import minesweeper.domain.Position

data class Running(override val board: Board) : GameState {

    override fun open(position: Position): GameState {
        val openBoard = board.open(position)
        return when {
            openBoard.isSafetyCellAllOpen() -> {
                Win(openBoard.endGame())
            }
            openBoard.isMine(position) -> {
                Lose(openBoard.endGame())
            }
            else -> {
                Running(openBoard)
            }
        }
    }

    override fun isFinished(): Boolean = false
}
