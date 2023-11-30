package domain

class Cell(
    val point: Point,
    val cellInfo: CellInfo,
) {
    fun installMine() = cellInfo.installMine()

    companion object {
        fun createEmptyCell(row: Int, col: Int): Cell {
            return Cell(Point(row, col), CellInfo())
        }
    }
}
