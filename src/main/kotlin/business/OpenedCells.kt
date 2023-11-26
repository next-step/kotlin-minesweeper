package business

class OpenedCells(private val height: Int, private val width: Int, openedPoints: List<Point> = listOf()) {
    private val _openedPoints = openedPoints.toMutableList()

    val openedPoints: List<Point>
        get() = _openedPoints.toList()

    init {
        require(openedPoints.none { outSize(it) }) { POINT_OVER_SIZE_ERROR_MESSAGE }
    }

    fun copy(): OpenedCells = OpenedCells(height, width, openedPoints)

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
    fun isAllOpened(minesSize: Int): Boolean = _openedPoints.size == (height * width) - minesSize
    private fun outSize(it: Point) = it.x < ZERO || it.x >= height || it.y < ZERO || it.y >= width

    companion object {
        private const val ZERO = 0
        private const val POINT_OVER_SIZE_ERROR_MESSAGE = "범위를 초과한 point가 존재한다"
    }
}
