package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.board.state.BoardPointState
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineFreeState
import next.step.minesweeper.domain.board.state.MineState

data class BoardPoint(private var state: BoardPointState) {

    fun plantMine() {
        state = CoveredState(MineState)
    }

    fun notifyMine() {
        state = state.notifyMine()
    }

    fun state(): BoardPointState = state

    fun uncover() {
        state = state.uncover()
    }

    fun canUncover(): Boolean = state is CoveredState && state.hasNoMine()

    fun isUncoveredMine(): Boolean = state == MineState

    fun isUncoveredMineFree(): Boolean = state == MineFreeState

    companion object {

        fun mineFree(): BoardPoint = BoardPoint(CoveredState(MineFreeState))
    }
}
