package dto

import domain.Cell

@JvmInline
value class CellDto(private val cell: Cell) {
    fun draw(): String = if (cell.isMine()) MINE else CLOSED
    fun mineNumber(): String = if (cell.isMine()) MINE else cell.mineNumber().toString()
    fun render(): String = if (!cell.isOpen() || cell.isMine()) CLOSED else mineNumber()

    companion object {
        private const val MINE = "*"
        private const val CLOSED = "C"
    }
}
