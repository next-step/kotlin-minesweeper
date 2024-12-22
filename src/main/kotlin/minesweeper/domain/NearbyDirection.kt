package minesweeper.domain

enum class NearbyDirection(private val coordinate: Position) {
    UP(Position(0, -1)),
    DOWN(Position(0, 1)),
    LEFT(Position(-1, 0)),
    RIGHT(Position(1, 0)),
    UP_LEFT(Position(-1, -1)),
    UP_RIGHT(Position(1, -1)),
    DOWN_LEFT(Position(-1, 1)),
    DOWN_RIGHT(Position(1, 1)),
    ;

    fun dx(): Int {
        return coordinate.x
    }

    fun dy(): Int {
        return coordinate.y
    }
}
