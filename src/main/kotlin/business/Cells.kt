package business

class Cells(cells: List<List<Cell>>) {
    private val _cells: MutableList<MutableList<Cell>> = cells.map(List<Cell>::toMutableList).toMutableList()
    private operator fun get(point: Point): Cell = _cells[point.x][point.y]
    private operator fun set(point: Point, cell: Cell) {
        _cells[point.x][point.y] = cell
    }

    fun copy(): Cells = Cells(_cells.map { it.map { cell: Cell -> cell } })
    fun isMine(point: Point): Boolean = this[point].isMine()
    fun isOpen(point: Point): Boolean = this[point].isOpen()

    fun open(point: Point) {
        this[point] = this[point].open()
        if (isSafePoint(point)) openAround(point)
    }

    fun countMines(point: Point): Int = point.aroundPoints()
        .filter { isValidPoint(it) }
        .count { isMine(it) }

    fun isAllOpen(): Boolean = _cells.all { it.all(Cell::isClear) }

    private fun isSafePoint(point: Point) = countMines(point) == Board.SAFE_MINE_COUNT

    private fun openAround(point: Point) = point.aroundPoints()
        .filter { isValidPoint(it) }
        .filter { !isOpen(it) }
        .forEach { open(it) }

    private fun isValidPoint(point: Point): Boolean =
        point.x in _cells.indices && point.y in _cells[FIRST_INDEX].indices

    fun processEachCellAndPoint(action: (Cell, Point) -> Unit, rowAction: () -> Unit) {
        _cells.forEachIndexed { x, row ->
            row.forEachIndexed { y, cell -> action(cell, Point(x, y)) }
            rowAction()
        }
    }

    companion object {
        const val FIRST_INDEX = 0

        fun of(boardInfo: BoardInfo, minePoints: List<Point>): Cells {
            return Cells(
                List(boardInfo.height) { y ->
                    List(boardInfo.width) { x ->
                        if (minePoints.contains(Point(x, y))) Cell(CellStatus.MINE) else Cell()
                    }
                }
            )
        }
    }
}
