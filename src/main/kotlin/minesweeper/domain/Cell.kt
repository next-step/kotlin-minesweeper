package minesweeper.domain

data class Cell(
    val position: Position
) {
    lateinit var cellState: CellState

    companion object {
        fun of(position: Position, minePositions: Positions, cellType: CellType): Cell {
            return Cell(position).apply {
                this.cellState = CellState.of(position, minePositions, cellType)
            }
        }
    }
}
