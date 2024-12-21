package domain

enum class Direction(val offset: Coordinate) {
    NORTH(Coordinate(Row(-1), Col(0))),
    SOUTH(Coordinate(Row(1), Col(0))),
    WEST(Coordinate(Row(0), Col(-1))),
    EAST(Coordinate(Row(0), Col(1))),
    NORTH_WEST(Coordinate(Row(-1), Col(-1))),
    NORTH_EAST(Coordinate(Row(-1), Col(1))),
    SOUTH_WEST(Coordinate(Row(1), Col(-1))),
    SOUTH_EAST(Coordinate(Row(1), Col(1))),
}
