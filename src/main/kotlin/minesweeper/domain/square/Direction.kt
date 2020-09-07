package minesweeper.domain.square

enum class Direction(val x: Int, val y: Int) {
    UP(-1, 0),
    UPPER_LEFT(-1, -1),
    UPPER_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN(1, 0),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1)
}
