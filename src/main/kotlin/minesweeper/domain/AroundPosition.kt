package minesweeper.domain

enum class AroundPosition(
    val dx: Int,
    val dy: Int,
) {
    UP(0, -1),
    UPPER_RIGHT(1, -1),
    RIGHT(1, 0),
    LOWER_RIGHT(1, 1),
    DOWN(0, 1),
    LOWER_LEFT(-1, 1),
    LEFT(-1, 0),
    UPPER_LEFT(-1, -1),
}
