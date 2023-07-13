package minesweeper.domain

enum class Direction(
    val xMove: Int,
    val yMove: Int,
) {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT(0, -1),
    UP_RIGHT(-1, 1),
    UP_LEFT(-1, -1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1),
}
