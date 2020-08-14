package minesweeper.domain

class Board(width: Int, length: Int) {
    val area = makeArea(width, length)

    private fun makeArea(width: Int, length: Int): List<Coordinate> {
        return (0 until width).flatMap { x -> (0 until length).map { y -> Coordinate(x, y) } }
    }

    fun findCoordinate(x: Int, y: Int): Coordinate = area.find { it.x == x && it.y == y } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")
}
