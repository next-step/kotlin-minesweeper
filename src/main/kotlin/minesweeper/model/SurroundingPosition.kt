package minesweeper.model

enum class SurroundingPosition(
    val x: Int,
    val y: Int,
) {
    LEFT_UP(-1, +1),
    UP(0, +1),
    RIGHT_UP(+1, +1),
    RIGHT(+1, 0),
    RIGHT_DOWN(+1, -1),
    DOWN(0, -1),
    LEFT_DOWN(-1, -1),
    LEFT(-1, 0);
}
