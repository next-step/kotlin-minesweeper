package minesweeper.domain.position

data class Position(
    private val x: Coordinate,
    private val y: Coordinate
) : Comparable<Position> {

    fun nearByPositions(): Set<Position> {
        return Direction.values()
            .map { next(it) }
            .toSet() - this
    }

    private fun next(direction: Direction): Position {
        return Position((x + direction.x) ?: x, (y + direction.y) ?: y)
    }

    override fun compareTo(other: Position): Int {
        return if (y.compareTo(other.y) == 0) {
            x.compareTo(other.x)
        } else {
            y.compareTo(other.y)
        }
    }

    private enum class Direction(
        val x: Int,
        val y: Int
    ) {
        UP(0, 1),
        DOWN(0, -1),
        RIGHT(1, 0),
        LEFT(-1, 0),

        UP_RIGHT(1, 1),
        UP_LEFT(-1, 1),
        DOWN_RIGHT(1, -1),
        DOWN_LEFT(-1, -1),
    }
}
