package dto

import domain.Cell

data class CellDto(
    val covered: String,
    val uncovered: String
) {
    constructor(cell: Cell) : this(
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

    companion object {
        private const val MINE = "*"
        private const val CLOSED = "C"
    }
}
