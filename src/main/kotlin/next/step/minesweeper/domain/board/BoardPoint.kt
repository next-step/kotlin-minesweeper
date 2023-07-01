package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.board.state.BoardPointState
import next.step.minesweeper.domain.board.state.CoveredState
import next.step.minesweeper.domain.board.state.MineState

data class BoardPoint(private var state: BoardPointState) {

    fun desc(): String = state.desc()

    fun plantMine() {
        state = MineState
    }

    companion object {
        fun covered() = BoardPoint(CoveredState)
    }
}