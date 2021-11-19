package dto

import domain.Cell

class CellDto(private val cell: Cell) {
    fun mineNumber(): String = if (cell.isMine()) "*" else cell.mineNumber().toString()
    override fun toString(): String = if (cell.isOpen() && !cell.isMine()) mineNumber() else "C"
}
