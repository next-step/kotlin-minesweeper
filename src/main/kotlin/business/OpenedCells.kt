package business

class OpenedCells(private val height: Int, private val width: Int, openedPoints: List<Point> = listOf()) {
    private val _openedPoints = openedPoints.toMutableList()

    val openedPoints: List<Point>
        get() = _openedPoints.toList()

    fun add(point: Point, mines: Mines) {
        if (point.x < 0 || point.x >= height || point.y < 0 || point.y >= width) return
        if (_openedPoints.contains(point)) return
        _openedPoints.add(point)
        if (mines.countMineAround(point) == 0) {
            openAround(point, mines)
        }
    }

    private fun openAround(point: Point, mines: Mines) =
        point.aroundPoints()
            .filter { !mines.contains(it) }
            .filter { !openedPoints.contains(it) }
            .forEach { add(it, mines) }

    fun contains(point: Point): Boolean {
        return _openedPoints.contains(point)
    }

    fun isAllOpened(mines: Mines): Boolean {
        return _openedPoints.size == (height * width) - mines.size()
    }
}
