package business

class Cells(cells: List<RowCells>) {
    private val _cells: MutableList<RowCells> = cells.toMutableList()
    private operator fun get(point: Point): Cell = _cells[point.x][point.y]
    private operator fun set(index: Int, rowCells: RowCells) {
        _cells[index] = rowCells
    }

    val cells: List<RowCells>
        get() = _cells.toList()

    init {
        _cells.forEachIndexed { x: Int, rowCells: RowCells ->
            rowCells.forEachIndexed { y: Int, cell: Cell ->
                if (cell.isMine()) addAroundMineCount(Point(x, y))
            }
        }
    }

    private fun addAroundMineCount(point: Point) {
        point.aroundPoints()
            .filter { isValidPoint(it) }
            .forEach { _cells[it.x] = _cells[it.x].addAroundMineCount(it.y) }
    }

    fun isMine(point: Point): Boolean = this[point].isMine()
    fun isOpen(point: Point): Boolean = this[point].isOpen()

    fun open(point: Point) {
        _cells[point.x] = _cells[point.x].open(point.y)
        if (isSafePoint(point)) openAround(point)
    }

    fun countMines(point: Point): Int = _cells[point.x][point.y].aroundMineCount
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

        fun of(boardInfo: BoardInfo, minePoints: List<Point>): Cells =
            Cells(List(boardInfo.height) { x -> RowCells.of(boardInfo.width, x, minePoints) })
    }
}
