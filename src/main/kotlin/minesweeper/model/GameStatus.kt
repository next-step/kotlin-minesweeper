package minesweeper.model

enum class GameStatus {
    ONGOING,
    WIN,
    LOST;

    fun isOngoing(): Boolean = this == ONGOING

    fun win(): Boolean = this == WIN

    fun lost(): Boolean = this == LOST
}
