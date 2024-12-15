package minesweeper

enum class CellType {
    MINE,
    EMPTY,
    ;

    fun isMine(): Boolean {
        return this == MINE
    }
}