package minesweeper.domain.cell

class MineCell(override val isOpen: Boolean = false) : Cell {

    override val isMine = true

    override fun open() = MineCell(isOpen = true)
}
