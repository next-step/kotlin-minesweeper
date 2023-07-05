package mine.sweeper.application.value

enum class SurroundPosition(val x: Int, val y: Int) {
    TOP_LEFT(-1, -1),
    TOP_CENTER(-1, 0),
    TOP_RIGHT(-1, 1),
    CENTER_LEFT(0, -1),
    CENTER_RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM_CENTER(1, 0),
    BOTTOM_RIGHT(1, 1),
}
