package minesweeper.domain.game

enum class MineAround(val x: Int, val y: Int) {
    TOP_LEFT(-1, -1),
    TOP(-1, 0),
    TOP_RIGHT(-1, 1),
    LEFT(0, -1),
    RIGHT(0, 1),
    BOTTOM_LEFT(1, -1),
    BOTTOM(1, 0),
    BOTTOM_RIGHT(1, 1);
}
