package minesweeper.domain.position

enum class Direction(private val xDirection: Int, private val yDirection: Int) {

    EAST(xDirection = 1, yDirection = 0),
    WEST(xDirection = -1, yDirection = 0),
    SOUTH(xDirection = 0, yDirection = 1),
    SOUTHEAST(xDirection = 1, yDirection = 1),
    SOUTHWEST(xDirection = -1, yDirection = 1),
    NORTH(xDirection = 0, yDirection = -1),
    NORTHEAST(xDirection = 1, yDirection = -1),
    NORTHWEST(xDirection = -1, yDirection = -1),
}
