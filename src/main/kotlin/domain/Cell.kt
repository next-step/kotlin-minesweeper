package domain

class Cell(
    private val point: Point,
    val cellInfo: CellInfo,
) {
    fun installMine() = cellInfo.installMine()

    fun findCellByNeighborMineCount(boardSettings: BoardSettings, board: List<CellList>) {
        cellInfo.findNeighborMineCount(boardSettings, board, point)
    }

    companion object {
        fun createEmptyCell(row: Int, col: Int): Cell {
            return Cell(Point(row, col), CellInfo())
        }
    }
}
