package minesweeper.entity

enum class Direction(val deltaX: Int, val deltaY: Int) {
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    RIGHT(0, 1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(1, 0),
    BOTTOM_LEFT(1, -1),
    LEFT(0, -1),
    TOP_LEFT(-1, -1),
}
