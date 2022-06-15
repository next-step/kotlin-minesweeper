package domain

enum class Directions(val row: Int, val col: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    LEFT_UP(-1, -1),
    LEFT_DOWN(1, -1),
    RIGHT_UP(-1, 1),
    RIGHT_DOWN(1, 1)
}