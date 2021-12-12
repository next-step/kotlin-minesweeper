package minesweeper.domain.board.state

sealed class Finish : GameState() {
    override val isFinished: Boolean = true
}
