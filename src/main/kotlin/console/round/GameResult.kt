package console.round

sealed class GameResult {
    open fun isLose(): Boolean = false

    data object Lose : GameResult() {
        override fun isLose(): Boolean = true
    }
}
