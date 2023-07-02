package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.board.state.BoardPointState
import next.step.minesweeper.domain.board.state.CoveredState
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

    companion object {

        fun covered(): BoardPoint = BoardPoint(CoveredState)

        fun mineFree(): BoardPoint = BoardPoint(MineFreeState)
    }
}
