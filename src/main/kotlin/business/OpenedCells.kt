package business

class OpenedCells(openedPoints: List<Point> = listOf()) {
    private val _openedPoints = openedPoints.toMutableList()

    val openedPoints: List<Point>
        get() = _openedPoints.toList()

    fun add(point: Point) {
        _openedPoints.add(point)
    }
}
