package minesweeper.domain.cell

class FlagCell(val cell: Cell) : Cell {

    override val isOpen = false

    override val isMine
        get() = cell.isMine

    override fun open() = cell.open()

    override fun flag() = cell
}
