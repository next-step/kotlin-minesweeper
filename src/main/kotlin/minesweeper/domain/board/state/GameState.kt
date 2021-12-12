package minesweeper.domain.board.state

sealed class GameState {
    abstract val isFinished: Boolean
}
