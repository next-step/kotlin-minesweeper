package minesweeper.domain.model

enum class Cell {
    NONE,
    MINE;

    fun isMine(): Boolean = this == MINE
}
