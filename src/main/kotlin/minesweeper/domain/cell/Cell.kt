package minesweeper.domain.cell

sealed interface Cell {

    fun isMine(): Boolean
}
