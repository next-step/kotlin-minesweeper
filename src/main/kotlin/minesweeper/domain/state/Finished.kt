package minesweeper.domain.state

import minesweeper.domain.Board
import minesweeper.domain.Position

sealed class Finished(override val board: Board) : GameState {

    override fun open(position: Position): GameState = throw UnsupportedOperationException(OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE)

    override fun isFinished(): Boolean = true

    companion object {
        const val OPEN_UNSUPPORTED_OPERATION_EXCEPTION_MESSAGE = "Finished 상태는 open()을 지원하지 않습니다."
    }
}
