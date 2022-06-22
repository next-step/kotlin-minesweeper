package minesweeper.domain

enum class Direction(val x: Int, val y: Int) {
    LEFT_TOP(-1, -1),
    LEFT_CENTER(-1, 0),
    LEFT_BOTTOM(-1, 1),
    CENTER_TOP(0, -1),
    CENTER_BOTTOM(0, 1),
    RIGHT_TOP(1, -1),
    RIGHT_CENTER(1, 0),
    RIGHT_BOTTOM(1, 1)
}
