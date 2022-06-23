package minesweeper.domain.cell

enum class NearbyDirection(val x: Int, val y: Int) {
    UP_LEFT(-1, 1),
    UP(0, 1),
    UP_RIGHT(1, 1),

    LEFT(-1, 0),
    RIGHT(1, 0),

    DOWN_LEFT(-1, -1),
    DOWN(0, -1),
    DOWN_RIGHT(1, -1)
}
