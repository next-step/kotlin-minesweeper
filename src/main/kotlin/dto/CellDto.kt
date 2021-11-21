package dto

import domain.Cell

class CellDto(private val cell: Cell) {
    fun mineNumber(): String = if (cell.isMine()) MINE else cell.mineNumber().toString()
    fun render(): String = if (cell.isOpen() && !cell.isMine()) mineNumber() else CLOSED
    override fun toString(): String = if (cell.isMine()) MINE else CLOSED

    companion object {
        private const val MINE = "*"
        private const val CLOSED = "C"
    }
}
