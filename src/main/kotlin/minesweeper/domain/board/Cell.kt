package minesweeper.domain.board

data class Cell(val position: Position, val cellType: CellType) {

    companion object {
        fun of(position: Position, cellType: CellType = NotMine()): Cell {
            return Cell(position, cellType)
        }
    }
}
