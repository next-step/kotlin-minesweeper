package domain

enum class Around(val y: Int, val x: Int) {
    RIGHT(0, 1),
    LEFT(0, -1),
    UP(-1, 0),
    UP_RIGHT(-1, 1),
    UP_LEFT(-1, -1),
    DOWN(1, 0),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1),
}
