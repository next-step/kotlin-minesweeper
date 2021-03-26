package minesweeper.domain

enum class Around(private val x: Int, private val y: Int) {
    LEFT_TOP(-1, -1), TOP(0, -1), TOP_RIGHT(1, -1),
    LEFT(-1, 0), RIGHT(1, 0),
    LEFT_BOTTOM(-1, 1), BOTTOM(0, 1), BOTTOM_RIGHT(1, 1);

    companion object {
        fun aroundPositions(x: Int, y: Int): List<Position> {
            return values().map { Position(it.x + x, it.y + y) }
        }
    }
}
