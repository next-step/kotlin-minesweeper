package minesweeper.domain.position

import minesweeper.util.cartesianProduct

data class Position(
    val row: Int,
    val col: Int
) : Comparable<Position> {
    init {
        require(row >= BASE_INDEX && col >= BASE_INDEX) {
            "row and col should be greater or equal than $BASE_INDEX [row:$row, col:$col]"
        }
    }

    override operator fun compareTo(other: Position): Int =
        comparator().compare(this, other)

    private operator fun plus(movingDirection: Direction): Position? =
        try {
            Position(row + movingDirection.row, col + movingDirection.col)
        } catch (e: IllegalArgumentException) {
            null
        }

    fun getAroundPositions(): List<Position> =
        Direction.values().mapNotNull { this + it }

    companion object {
        const val BASE_INDEX: Int = 0

        fun indexRange(size: Int): IntRange =
            size.indexRange()

        fun getAllPositionList(height: Int, width: Int): List<Position> =
            height.indexRange() comma width.indexRange()

        private fun comparator(): Comparator<Position> =
            compareBy({ it.row }, { it.col })
    }
}

fun Int.indexRange(): IntRange = if (this > 0) (0 until this) else IntRange.EMPTY

infix fun IntRange.comma(intRange: IntRange): List<Position> =
    listOf(this, intRange).cartesianProduct().map { it[0] comma it[1] }

infix fun Int.comma(other: Int): Position = Position(this, other)
