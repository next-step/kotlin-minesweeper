package domain

enum class RectanglePosition(val row: Int, val column: Int) {
    LEFT_TOP(-1, -1),
    TOP(-1, 0),
    RIGHT_TOP(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    LEFT_BOTTOM(1, -1),
    BOTTOM(1, 0),
    RIGHT_BOTTOM(1, 1),
}
