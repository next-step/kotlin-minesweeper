package domain

class Board private constructor(
    private val cells: Cells,
) {
    fun cells(): Cells {
        return cells
    }

    fun placeMines(
        minePlacer: MinePlacer,
        mineCount: Int,
    ): Board {
        val cells = minePlacer.placeMines(cells, mineCount)
        return Board(cells)
    }

    fun openAllCells(): List<OpenedCell> {
        return cells.allCells().map { cell ->
            val symbol =
                if (cell.hasMine) {
                    MINE_SYMBOL
                } else {
                    cells.countAdjacentMines(cell).toString()
                }
            OpenedCell(cell.position, symbol)
        }
    }

    companion object {
        private const val MINE_SYMBOL = "*"

        fun from(cells: Cells): Board {
            return Board(cells)
        }
    }
}
