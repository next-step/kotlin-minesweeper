package domain

enum class Directions(val value: Pair<Int, Int>) {
    LEFT_UP_DIAGONAL(-1 to -1),
    UP(-1 to 0),
    RIGHT_UP_DIAGONAL(1 to -1),
    RIGHT(0 to 1),
    RIGHT_DOWN_DIAGONAL(1 to 1),
    DOWN(1 to 0),
    LEFT_DOWN_DIAGONAL(1 to -1),
    LEFT(0 to -1)
}
