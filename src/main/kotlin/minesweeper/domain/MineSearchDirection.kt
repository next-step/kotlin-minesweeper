package minesweeper.domain

enum class MineSearchDirection(
    val x: Int,
    val y: Int,
) {
    LEFT_UP(-1, -1),
    UP(-1, 0),
    RIGHT_UP(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT_DOWN(1, -1),
    RIGHT_DOWN(1, 1),
    ;
}
