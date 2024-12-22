package minesweeper.domain

enum class NearbyDirection(private val coordinate: Coordinate) {
    UP(Coordinate(0, -1)),
    DOWN(Coordinate(0, 1)),
    LEFT(Coordinate(-1, 0)),
    RIGHT(Coordinate(1, 0)),
    UP_LEFT(Coordinate(-1, -1)),
    UP_RIGHT(Coordinate(1, -1)),
    DOWN_LEFT(Coordinate(-1, 1)),
    DOWN_RIGHT(Coordinate(1, 1)),
    ;

    fun dx(): Int {
        return coordinate.x
    }

    fun dy(): Int {
        return coordinate.y
    }
}
