package minesweeper.model

enum class CellType {
    MINE,
    NON_MINE;

    fun isMine() = this == MINE
}
