package business

class OpenedCells(private val limit: OpenedCellsLimit, openedPoints: List<Point> = listOf()) {
    private val _openedPoints = openedPoints.toMutableList()

    val openedPoints: List<Point>
        get() = _openedPoints.toList()

    init {
        require(openedPoints.none { outSize(it) }) { POINT_OVER_SIZE_ERROR_MESSAGE }
    }

    fun copy(): OpenedCells = OpenedCells(limit, openedPoints)

    fun add(point: Point, mines: Mines) {
        if (outSize(point)) return
        if (_openedPoints.contains(point)) return
        _openedPoints.add(point)
        if (mines.countMineAround(point) == ZERO) openAround(point, mines)
    }

    private fun openAround(point: Point, mines: Mines) =
        point.aroundPoints()
            .filter { !mines.contains(it) }
            .filter { !openedPoints.contains(it) }
            .forEach { add(it, mines) }

    fun contains(point: Point): Boolean = _openedPoints.contains(point)
    fun isAllOpened(minesSize: Int): Boolean = limit.isAllOpened(minesSize, _openedPoints.size)
    private fun outSize(it: Point) = limit.outSize(it)

    companion object {
        private const val ZERO = 0
        private const val POINT_OVER_SIZE_ERROR_MESSAGE = "범위를 초과한 point가 존재한다"
        fun of(height: Int, width: Int): OpenedCells = OpenedCells(OpenedCellsLimit(height, width))
        fun of(height: Int, width: Int, openedPoints: List<Point>): OpenedCells =
            OpenedCells(OpenedCellsLimit(height, width), openedPoints)
    }
}

data class OpenedCellsLimit(val height: Int, val width: Int) {
    fun outSize(it: Point) = it.x < ZERO || it.x >= height || it.y < ZERO || it.y >= width
    fun isAllOpened(minesSize: Int, openedPointsSize: Int): Boolean = openedPointsSize == (height * width) - minesSize

    companion object {
        private const val ZERO = 0
    }
}
