package minesweeper

class Board(
    private val cells: Cells,
) {
    fun checkMine(position: Position): Boolean {
        return cells.checkMine(position)
    }

    companion object {
        fun initializeBoard(dimensions: Dimensions, mineCount: Int, fixture: PositionProvider): Board {
            val cells = Cells(fixture.provide(dimensions, mineCount))
            return Board(cells)
        }
    }
}
