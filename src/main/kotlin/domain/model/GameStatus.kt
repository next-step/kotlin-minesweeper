package domain.model

sealed class GameStatus

object Ready : GameStatus()

object Running : GameStatus()

object GameOver : GameStatus()

object Win : GameStatus()
