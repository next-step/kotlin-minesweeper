package minesweeper.domain.position

enum class Direction(
    val row: Int,
    val col: Int
) {
    UP_AND_LEFT(-1, -1),
    UP(-1, 0),
    UP_AND_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN_AND_LEFT(1, -1),
    DOWN(1, 0),
    DOWN_AND_RIGHT(1, 1);
}
