package minesweeper.domain

data class Cell(
    val position: Position,
    val cellType: CellType
) {

    companion object {
        fun of(position: Position, cellType: CellType): Cell {
            return Cell(position, cellType)
        }
    }
}
