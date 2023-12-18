package minesweeper.domain.model.cell

enum class CellState {
    NONE,
    MINE;

    fun isMine(): Boolean = this == MINE
}
