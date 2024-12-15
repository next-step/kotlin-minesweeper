package minesweeper

class Board(
    private val cells: Cells,
) {
    fun checkMine(position: Position): Boolean {
        return cells.checkMine(position)
    }

    fun draw(): List<List<CellType>> {
        val result = mutableListOf<List<CellType>>() // 최종 결과를 담을 리스트
        val cellSize = cells.rowSize()
        while (cellSize > result.size) {
            val row = cells.rowAt(result.size) ?: break

            result.add(row)
        }

        return result
    }

    companion object {
        fun initializeBoard(dimensions: Dimensions, mineCount: Int, fixture: PositionProvider): Board {
            val cells = Cells(fixture.provide(dimensions, mineCount))
            return Board(cells)
        }
    }
}
