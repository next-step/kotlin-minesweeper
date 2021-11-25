package dto

import domain.Cell

@JvmInline
value class CellDto(private val cell: Cell) {
    override fun toString(): String = if (cell.isMine()) MINE else CLOSED

    companion object {
        private const val MINE = "*"
        private const val CLOSED = "C"
    }
}
