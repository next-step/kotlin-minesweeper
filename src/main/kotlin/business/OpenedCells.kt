package business

class OpenedCells(private val height: Int, private val width: Int, openedPoints: List<Point> = listOf()) {
    private val _openedPoints = openedPoints.toMutableList()

    val openedPoints: List<Point>
        get() = _openedPoints.toList()

    fun add(point: Point) {
        if (point.x < 0 || point.x >= height || point.y < 0 || point.y >= width) return
        if (_openedPoints.contains(point)) return
        _openedPoints.add(point)
    }

    fun openAround(point: Point, mines: Mines) = point.aroundPoints()
        .filter { !mines.contains(it) }
        .filter { !openedPoints.contains(it) }
        .forEach { add(it) }
}
