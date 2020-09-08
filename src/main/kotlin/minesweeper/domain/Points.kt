package minesweeper.domain

class Points(coordinates: Coordinates, private val mineCoordinates: List<Coordinate> = listOf()) {
    private val _allPoints: MutableList<Point> = makePoint(coordinates, mineCoordinates)
    val allPoints: List<Point>
        get() = _allPoints.toList()

    init {
        _allPoints.forEach { it.setMineCount(getAroundMines(it)) }
    }

    private fun makePoint(coordinates: Coordinates, mineCoordinates: List<Coordinate>): MutableList<Point> {
        return coordinates.coordinates.map { ClosePoint(it, mineCoordinates.contains(it)) }.toMutableList()
    }

    private fun getAroundMines(point: Point): Int {
        return Direction.values().count { checkMine(point.coordinate move it) }
    }

    private fun checkMine(coordinate: Coordinate): Boolean {
        findPoint(coordinate.x, coordinate.y) ?: return false
        return mineCoordinates.contains(coordinate)
    }

    fun findPoint(x: Int, y: Int): Point? =
        _allPoints.find { it.isItCoordinate(Coordinate(x, y)) }

    fun getClosePointsSize(): Int {
        return _allPoints.count { !it.isOpen() }
    }

    fun open(point: Point): Point {
        val openPoint = point.open() ?: throw IllegalArgumentException("해당 point 는 이미 open된 포인트 입니다")
        _allPoints[_allPoints.indexOf(point)] = openPoint
        return openPoint
    }
}
