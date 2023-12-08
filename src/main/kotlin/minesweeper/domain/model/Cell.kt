package minesweeper.domain.model

enum class Cell {
    NONE,
    MINE;

    fun isMine(): Boolean = this == MINE

    companion object {
        fun create(isMine: Boolean): Cell {
            return if (isMine) MINE else NONE
        }
    }
}
