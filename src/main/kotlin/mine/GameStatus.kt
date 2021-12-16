package mine

sealed class GameStatus {
    object GAMEOVER : GameStatus()
    object CONTINUE : GameStatus()
    object WIN : GameStatus()
}
