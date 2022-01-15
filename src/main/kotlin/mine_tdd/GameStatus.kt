package mine_tdd

sealed class GameStatus {
    object OVER : GameStatus()
    object WIN : GameStatus()
    object CONTINUE : GameStatus()
}
