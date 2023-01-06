package domain.state

import domain.Board
import domain.Position

sealed class Finished(override val board: Board) : State {
    override fun open(position: Position): State {
        throw IllegalArgumentException("게임이 종료되었습니다.")
    }

    override fun isFinished(): Boolean = true

    data class Clear(override val board: Board) : Finished(board)

    data class Boom(override val board: Board) : Finished(board)
}
