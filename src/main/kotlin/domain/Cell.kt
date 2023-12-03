package domain

class Cell(
    val point: Point = Point(0, 0),
    val cellInfo: CellInfo = CellInfo(),
) {
    fun installMine() = cellInfo.installMine()

    fun findCellByNeighborMineCount(boardSettings: BoardSettings, board: List<CellList>) {
        cellInfo.findNeighborMineCount(boardSettings, board, point)
    }

    fun createEmptyCell(row: Int, col: Int): Cell {
        return Cell(Point(row, col), CellInfo())
    }

    fun isNotMine(): Boolean = cellInfo.isNotMine()

    fun openCell() = cellInfo.openCell()
}
