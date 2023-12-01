package domain

class Cell(
    private val point: Point,
    val cellInfo: CellInfo,
) {
    fun installMine() = cellInfo.installMine()

    fun createCell(boardSettings: BoardSettings, board: List<CellList>): Cell = Cell(point, cellInfo.createCellInfo(boardSettings, board, point))

    companion object {
        fun createEmptyCell(row: Int, col: Int): Cell {
            return Cell(Point(row, col), CellInfo())
        }
    }
}
