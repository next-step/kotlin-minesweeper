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

    companion object {
        fun initializeBoard(
            dimensions: Dimensions,
            positionProvider: CellProvider,
        ): Board {
            val cells = Cells.create(positionProvider.provide(dimensions))
            if (cells.mineCount != dimensions.mineCount) {
                throw IllegalArgumentException("지뢰 갯수가 일치하지 않습니다.")
            }

            return Board(cells)
        }
    }
}