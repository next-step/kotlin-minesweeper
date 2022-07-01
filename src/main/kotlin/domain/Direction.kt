package domain

enum class Direction(val diffColumn: Int, val diffRow: Int) {
    UP(0, -1),
    UPPER_RIGHT(1, -1),
    RIGHT(1, 0),
    BOTTOM_RIGHT(1, 1),
    BOTTOM(0, 1),
    BOTTOM_LEFT(-1, 1),
    LEFT(-1, 0),
    UPPER_LEFT(-1, -1)
}
