package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.board.state.BoardPointState
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState
import next.step.minesweeper.domain.board.state.NearMineState

data class BoardPoint(private var state: BoardPointState) {

    fun plantMine() {
        state = MineState
    }

    fun state() = state

    fun notifyMine() {
        when (state) {
            MineFreeState -> state = NearMineState.one()
            is NearMineState -> (state as NearMineState).increase()
            else -> {}
        }
    }

    companion object {

        fun covered(): BoardPoint = BoardPoint(CoveredState)

        fun mineFree(): BoardPoint = BoardPoint(MineFreeState)
    }
}
