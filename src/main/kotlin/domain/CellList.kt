package domain

class CellList(val cells: List<Cell>) {
    fun createCellList(boardSettings: BoardSettings, board: List<CellList>): CellList {
        return CellList(cells.map { it.createCell(boardSettings, board) })
    }

    companion object {
        fun createEmptyRow(row: Int, cols: Int): CellList {
            return CellList(
                (0 until cols).map { col ->
                    Cell.createEmptyCell(row, col)
                }
            )
        }
    }
}
