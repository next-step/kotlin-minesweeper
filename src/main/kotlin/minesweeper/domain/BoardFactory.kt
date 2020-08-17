package minesweeper.domain

object BoardFactory {
    fun makeBoard(width: Int, length: Int, getMineCoordinates: (coordinates: List<Coordinate>) -> List<Coordinate>): Board {
        val coordinates = makeCoordinate(width, length)
        val points = makePoint(coordinates, getMineCoordinates(coordinates))
        return Board(points)
    }

    private fun makeCoordinate(width: Int, length: Int): List<Coordinate> {
        return (FIRST until length).flatMap { y -> (FIRST until width).map { x -> Coordinate(x, y) } }
    }

    private fun makePoint(coordinates: List<Coordinate>, mineCoordinates: List<Coordinate>): List<Point> {
        return coordinates.map { setMine(mineCoordinates.contains(it), it) }
    }

    private fun setMine(isContain: Boolean, coordinate: Coordinate): Point {
        if (isContain) {
            return Point(coordinate, true)
        }
        return Point(coordinate)
    }

    fun makeMineCoordinates(coordinates: List<Coordinate>, mineCount: Int): List<Coordinate> = coordinates.shuffled().take(mineCount)

    private const val FIRST = 0
}
