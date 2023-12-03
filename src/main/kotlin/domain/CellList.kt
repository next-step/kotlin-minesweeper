package domain

class CellList(val cells: List<Cell>) {
    fun findCellListByNeighborMineCount(boardSettings: BoardSettings, board: List<CellList>) {
        cells.map { it.findCellByNeighborMineCount(boardSettings, board) }
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
