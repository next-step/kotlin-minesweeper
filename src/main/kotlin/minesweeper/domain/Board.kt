package minesweeper.domain

class Board(width: Int, length: Int) {
    val coordinates = makeCoordinate(width, length)
    var points: List<Point> = listOf()
        private set

    private fun makeCoordinate(width: Int, length: Int): List<Coordinate> {
        return (FIST until width).flatMap { x -> (FIST until length).map { y -> Coordinate(x, y) } }
    }

    fun makePoint(mineCount: Int = 0) {
        val mineCoordinates = getMineCoordinate(mineCount)
        points = coordinates.map { checkMine(mineCoordinates.contains(it), it) }
    }

    private fun getMineCoordinate(mineCount: Int): List<Coordinate> = coordinates.shuffled().take(mineCount)

    private fun checkMine(isContain: Boolean, coordinate: Coordinate): Point {
        if (isContain) {
            return Point(coordinate, true)
        }
        return Point(coordinate)
    }

    fun findPoint(x: Int, y: Int): Point =
        points.find { it.isIt(Coordinate(x, y)) } ?: throw IllegalArgumentException("$x, $y 좌표는 없습니다.")

    fun countMine(): Int = points.filter { it.isMine }.size

    companion object {
        const val FIST = 0
    }
}
