package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.board.state.BoardPointState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState

data class BoardPoint(private var state: BoardPointState) {

    fun plantMine() {
        state = MineState
    }

    fun notifyMine() {
        state = state.notifyMine()
    }

    fun state(): BoardPointState = state

    fun cover() {
        state = state.cover()
    }

    fun uncover() {
        state = state.uncover()
    }

    companion object {

        fun mineFree(): BoardPoint = BoardPoint(MineFreeState)
    }
}
