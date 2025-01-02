package domain

enum class Direction(val offset: Coordinate) {
    NORTH(Coordinate(-1, 0)),
    SOUTH(Coordinate(1, 0)),
    WEST(Coordinate(0, -1)),
    EAST(Coordinate(0, 1)),
    NORTH_WEST(Coordinate(-1, -1)),
    NORTH_EAST(Coordinate(-1, 1)),
    SOUTH_WEST(Coordinate(1, -1)),
    SOUTH_EAST(Coordinate(1, 1)),
}
