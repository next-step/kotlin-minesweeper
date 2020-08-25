package minesweeper.domain

class Points(coordinates: Coordinates, mineCoordinates: List<Coordinate> = listOf()) {
    val allPoints: List<Point> = makePoint(coordinates, mineCoordinates)

    init {
        allPoints.forEach { it.setMineCount(getAroundMines(it)) }
        allPoints.forEach { it.closePoint() }
    }

    private fun makePoint(coordinates: Coordinates, mineCoordinates: List<Coordinate>): List<Point> {
        return coordinates.coordinates.map { setMine(mineCoordinates.contains(it), it) }
    }

    private fun setMine(isContain: Boolean, coordinate: Coordinate): Point {
        if (isContain) {
            return Mine(coordinate)
        }
        return NotMine(coordinate)
    }

    private fun getAroundMines(point: Point): Int {
        return Direction.values().count { checkMine(point.coordinate move it) }
    }

    private fun checkMine(coordinate: Coordinate): Boolean {
        val point = findPoint(coordinate.x, coordinate.y) ?: return false
        return point.isMine()!!
    }

    fun findPoint(x: Int, y: Int): Point? =
        allPoints.find { it.isItCoordinate(Coordinate(x, y)) }

    fun getClosePointsSize(): Int {
        return allPoints.count { !it.isOpen }
    }
}
