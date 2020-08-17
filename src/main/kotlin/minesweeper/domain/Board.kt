package minesweeper.domain

class Board(val points: List<Point>) {
    init {
        points.forEach { it.setMineCount(getAroundMines(it)) }
    }

    fun findPoint(x: Int, y: Int): Point =
        points.find { it.isItCoordinate(Coordinate(x, y)) } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")

    fun countMine(): Int = points.filter { it.hasMine }.size

    private fun getAroundMines(point: Point): Int {
        return Direction.values().filter { isMine(it.getCoordinate(point.coordinate)) }.size
    }

    private fun isMine(coordinate: Coordinate): Boolean {
        return try {
            findPoint(coordinate.x, coordinate.y).hasMine
        } catch (e: Exception) {
            false
        }
    }
}
