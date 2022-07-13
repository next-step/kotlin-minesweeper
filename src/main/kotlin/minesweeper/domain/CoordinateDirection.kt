package minesweeper.domain

enum class CoordinateDirection(
    val x: CoordinateValue,
    val y: CoordinateValue,
) {
    EAST(CoordinateValue(1), CoordinateValue(0)),
    WEST(CoordinateValue(-1), CoordinateValue(0)),
    SOUTH(CoordinateValue(0), CoordinateValue(1)),
    NORTH(CoordinateValue(0), CoordinateValue(-1)),

    NORTH_EAST(CoordinateValue(1), CoordinateValue(-1)),
    NORTH_WEST(CoordinateValue(-1), CoordinateValue(-1)),
    SOUTH_EAST(CoordinateValue(1), CoordinateValue(1)),
    SOUTH_WEST(CoordinateValue(-1), CoordinateValue(1)),
    ;

    companion object {
        fun around(coordinate: Coordinate): List<Coordinate> = values().map {
            Coordinate(
                x = coordinate.x + it.x,
                y = coordinate.y + it.y
            )
        }
    }
}
