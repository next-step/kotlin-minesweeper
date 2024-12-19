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

    companion object {
        fun from(cells: Cells): Board {
            return Board(cells)
        }
    }
}
