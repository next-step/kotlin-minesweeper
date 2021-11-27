package dto

import domain.Cell

data class CellDto private constructor(
    val covered: String,
    val uncovered: String
) {
    companion object {
        private const val MINE = "*"
        private const val CLOSED = "C"
        fun from(cell: Cell): CellDto = CellDto(
            if (!cell.isOpen() || cell.hasMine()) {
                CLOSED
            } else {
                cell
                    .mineNumber()
                    .toString()
            },
            if (cell.hasMine()) {
                MINE
            } else {
                cell
                    .mineNumber()
                    .toString()
            }
        )
    }
}
