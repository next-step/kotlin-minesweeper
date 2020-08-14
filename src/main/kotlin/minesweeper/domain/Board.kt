package minesweeper.domain

class Board(width: Int, length: Int) {
    val points = makePoint(width, length)

    private fun makePoint(width: Int, length: Int): List<Point> = makeCoordinate(width, length).map { Point(it, false) }

    private fun makeCoordinate(width: Int, length: Int): List<Coordinate> {
        return (FIST until width).flatMap { x -> (FIST until length).map { y -> Coordinate(x, y) } }
    }

    fun findCoordinate(x: Int, y: Int): Coordinate = points.map { it.coordinate }.find { it.x == x && it.y == y } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")

    companion object {
        const val FIST = 0
    }
}
