package minesweeper.domain

enum class Direction(val x: Int, val y: Int) {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    fun getCoordinate(coordinate: Coordinate): Coordinate {
        val x = x + coordinate.x
        val y = y + coordinate.y
        return Coordinate(x, y)
    }
}
