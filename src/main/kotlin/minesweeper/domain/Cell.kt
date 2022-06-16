package minesweeper.domain

data class Cell(
    val position: Position,
    val cellType: CellType
) {

    companion object {
        fun of(position: Position, isMine: Boolean): Cell {
            return Cell(position, if (isMine) CellType.MINE else CellType.NON_MINE)
        }
    }
}
