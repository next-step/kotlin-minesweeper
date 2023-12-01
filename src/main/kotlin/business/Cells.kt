package business

class Cells(cells: List<Cell>) {
    constructor(vararg cells: Cell) : this(cells.toList())

    private val _cells: MutableList<Cell> = cells.toMutableList()
    private val heights: IntRange =
        _cells.map(Cell::point).map(Point::x).distinct().sorted().let { it.first()..it.last() }
    private val widths: IntRange =
        _cells.map(Cell::point).map(Point::y).distinct().sorted().let { it.first()..it.last() }

    val cells: List<Cell>
        get() = _cells.toList()

    init {
        _cells.map {
            if (it.isMine() && !it.isOpen()) {
                _cells[_cells.indexOf(it)] = it.open()
                addAroundMineCount(it.point)
            }
        }
    }

    fun isMine(point: Point): Boolean = getCell(point).isMine()
    fun isOpen(point: Point): Boolean = getCell(point).isOpen()
    fun countMines(point: Point): Int = getCell(point).aroundMineCount
    fun isAllOpen(): Boolean = _cells.all(Cell::isClear)

    fun processEachCell(action: (Cell) -> Unit, rowAction: () -> Unit) {
        heights.forEach { height ->
            widths.forEach { width ->
                val point = Point(height, width)
                action(getCell(point))
            }
            rowAction()
        }
    }

    fun open(point: Point) {
        val element = getCell(point)
        _cells.remove(element)
        _cells.add(element.open())
        if (isSafePoint(point)) openAround(point)
    }

    private fun getCell(point: Point): Cell = _cells.first { it.point == point }
    private fun containsPoint(point: Point) = _cells.any { cell: Cell -> cell.point == point }
    private fun isClose(point: Point): Boolean = !isOpen(point)
    private fun isSafePoint(point: Point) = countMines(point) == Board.SAFE_MINE_COUNT

    private fun addAroundMineCount(point: Point) {
        point.aroundPoints().filter(::containsPoint)
            .forEach { _cells[_cells.indexOf(getCell(it))] = getCell(it).addAroundMineCount() }
    }

    private fun openAround(point: Point) =
        point.aroundPoints().filter(::containsPoint).filter(::isClose).forEach(::open)

    companion object {
        fun of(minePoints: List<Point>, allPoints: List<Point>): Cells = Cells(
            allPoints.map { point -> if (minePoints.contains(point)) Cell.mine(point) else Cell.empty(point) }.toList()
        )
    }
}
