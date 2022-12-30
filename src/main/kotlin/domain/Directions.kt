package domain

enum class Directions(val coordinate: Coordinate) {
    LEFT_UP_DIAGONAL(Coordinate(-1 to -1)),
    UP(Coordinate(-1 to 0)),
    RIGHT_UP_DIAGONAL(Coordinate(-1 to 1)),
    RIGHT(Coordinate(0 to 1)),
    RIGHT_DOWN_DIAGONAL(Coordinate(1 to 1)),
    DOWN(Coordinate(1 to 0)),
    LEFT_DOWN_DIAGONAL(Coordinate(1 to -1)),
    LEFT(Coordinate(0 to -1))
}
