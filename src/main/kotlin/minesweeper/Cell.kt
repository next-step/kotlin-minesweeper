package minesweeper

class Cell(private val type: CellType = CellType.EMPTY, val position: Position) {
    val isMine: Boolean
        get() = type.isMine()

    companion object {
        fun createMine(): Cell {
            return Cell(CellType.MINE, Position(1, 1))
        }
    }
}
