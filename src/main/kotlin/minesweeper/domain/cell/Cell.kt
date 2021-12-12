package minesweeper.domain.cell

sealed interface Cell {

    val isOpen: Boolean

    val isMine: Boolean

    fun open(): Cell
}
