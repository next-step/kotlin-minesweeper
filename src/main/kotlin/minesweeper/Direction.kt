package minesweeper

enum class Direction(val dx: Int, val dy: Int) {
    NONE(0, 0),
    UP_LEFT(-1, -1),
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN_LEFT(1, -1),
    DOWN(1, 0),
    DOWN_RIGHT(1, 1),
}
