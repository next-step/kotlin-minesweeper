package minesweeper.domain.board.state

sealed class GameState {
    abstract fun isFinished(): Boolean
}

object Running : GameState() {
    override fun isFinished(): Boolean = false
}

sealed class Finish : GameState() {
    override fun isFinished(): Boolean = true
}

object Win : Finish()

object Lose : Finish()