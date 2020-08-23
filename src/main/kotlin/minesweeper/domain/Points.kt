package minesweeper.domain

class Points(coordinates: Coordinates, mineCoordinates: List<Coordinate> = listOf()) {
    private val allPoints = makePoint(coordinates, mineCoordinates)

    init {
        allPoints.forEach { it.setMineCount(getAroundMines(it)) }
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
        return try {
            findPoint(coordinate.x, coordinate.y).isMine()
        } catch (e: Exception) {
            false
        }
    }

    fun findPoint(x: Int, y: Int): Point =
        allPoints.find { it.isItCoordinate(Coordinate(x, y)) } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")

    fun forEach(f: (point: Point) -> Unit) {
        allPoints.forEach { f(it) }
    }

    fun getNotOpenPoints(): List<Point> {
        return allPoints.filterNot { it.isMine() || it.isOpen }
    }
}
