package minesweeper.domain.board.state

object Running : GameState() {
    override val isFinished: Boolean = false
}
