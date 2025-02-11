package cell

sealed interface GameResult {
    data object LOSE : GameResult

    data object CONTINUE : GameResult
}
