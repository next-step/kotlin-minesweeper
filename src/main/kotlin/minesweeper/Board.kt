package minesweeper

class Board private constructor(
    private val cells: Cells,
) {
    fun checkMine(position: Position): Boolean {
        return cells.checkMine(position)
    }

    fun draw(): BoardDrawing {
        return BoardDrawing.create(cells)
    }

    fun detectMines() {
        cells.detectMines()
    }

    companion object {
        fun initializeBoard(
            dimensions: Dimensions,
            positionProvider: CellProvider,
        ): Board {
            val cells = positionProvider.provide(dimensions)

            return Board(cells)
        }
    }
}
