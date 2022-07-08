package minesweeper.model

enum class GameStatus {
    ONGOING,
    WIN,
    LOST;

    val isOngoing
        get(): Boolean = this == ONGOING

    val win
        get(): Boolean = this == WIN

    val lost
        get(): Boolean = this == LOST
}
