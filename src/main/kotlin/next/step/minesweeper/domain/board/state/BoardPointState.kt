package next.step.minesweeper.domain.board.state

sealed class BoardPointState {
    abstract fun desc(): String
}
