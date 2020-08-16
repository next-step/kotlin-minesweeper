package minesweeper.domain

class Board(width: Int, length: Int, mineCount: Int = 0) {
    private val coordinates = makeCoordinate(width, length)
    val points: List<Point> = makePoint(mineCount)

    private fun makeCoordinate(width: Int, length: Int): List<Coordinate> {
        return (FIRST until length).flatMap { y -> (FIRST until width).map { x -> Coordinate(x, y) } }
    }

    private fun makePoint(mineCount: Int = 0): List<Point> {
        val mineCoordinates = getMineCoordinate(mineCount)
        return coordinates.map { setMine(mineCoordinates.contains(it), it) }
    }

    private fun getMineCoordinate(mineCount: Int): List<Coordinate> = coordinates.shuffled().take(mineCount)

    private fun setMine(isContain: Boolean, coordinate: Coordinate): Point {
        if (isContain) {
            return Point(coordinate, true)
        }
        return Point(coordinate)
    }

    fun findPoint(x: Int, y: Int): Point =
        points.find { it.isItCoordinate(Coordinate(x, y)) } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")

    fun countMine(): Int = points.filter { it.hasMine }.size

    companion object {
        const val FIRST = 0
    }
}
