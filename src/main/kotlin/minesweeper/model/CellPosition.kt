package minesweeper.model

data class CellPosition(
    val x: Position,
    val y: Position,
) : Comparable<CellPosition> {

    fun findSurroundingCellPositions(): Set<CellPosition> = SurroundingPosition.values()
        .map { this + it }
        .toSet()

    operator fun plus(position: SurroundingPosition): CellPosition = CellPosition(x + position.x, y + position.y)

    override fun compareTo(other: CellPosition): Int {
        if (y.isGreaterThan(other.y)) {
            return 1
        }

        if (y.isLessThan(other.y)) {
            return -1
        }

        if (x.isGreaterThan(other.x)) {
            return 1
        }

        if (x.isLessThan(other.x)) {
            return -1
        }

        return 0
    }

    companion object {
        fun of(x: Int, y: Int): CellPosition = CellPosition(Position.from(x), Position.from(y))
    }
}
