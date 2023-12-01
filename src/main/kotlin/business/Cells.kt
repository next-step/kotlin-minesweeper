package business

class Cells(cells: List<RowCells>) {
    private val _cells: MutableList<RowCells> = cells.toMutableList()
    private operator fun get(point: Point): Cell = _cells[point.x][point.y]

    val cells: List<RowCells>
        get() = _cells.toList()

    fun isMine(point: Point): Boolean = this[point].isMine()
    fun isOpen(point: Point): Boolean = this[point].isOpen()

    fun open(point: Point) {
        _cells[point.x] = _cells[point.x].open(point.y)
        if (isSafePoint(point)) openAround(point)
    }

    fun countMines(point: Point): Int = point.aroundPoints()
        .filter { isValidPoint(it) }
        .count { isMine(it) }

    fun isAllOpen(): Boolean = _cells.all(RowCells::isAllOpen)

    private fun isSafePoint(point: Point) = countMines(point) == Board.SAFE_MINE_COUNT

    private fun openAround(point: Point) = point.aroundPoints()
        .filter { isValidPoint(it) }
        .filter { !isOpen(it) }
        .forEach { open(it) }

    private fun isValidPoint(point: Point): Boolean =
        point.x in _cells.indices && point.y in _cells[FIRST_INDEX].indices

    fun processEachCellAndPoint(action: (Cell, Point) -> Unit, rowAction: () -> Unit) {
        _cells.forEachIndexed { x: Int, row: RowCells ->
            row.processEachCellAndPoint(x, action)
            rowAction()
        }
    }

    companion object {
        const val FIRST_INDEX = 0

        fun of(boardInfo: BoardInfo, minePoints: List<Point>): Cells {
            return Cells(
                List(boardInfo.height) { y ->
                    RowCells(
                        List(boardInfo.width) { x ->
                            Cell(
                                cellStatus = if (minePoints.contains(Point(x, y))) CellStatus.MINE else CellStatus.EMPTY
                            )
                        }
                    )
                }
            )
        }
    }
}
