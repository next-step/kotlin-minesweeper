package map

enum class RelativeDirection(val x: Int, val y: Int) {
    UP_LEFT(-1, -1),
    UP_MIDDLE(-1, 0),
    UP_RIGHT(-1, 1),
    MID_LEFT(0, -1),
    MID_RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM_MIDDLE(1, 0),
    BOTTOM_RIGHT(1, 1)
}
