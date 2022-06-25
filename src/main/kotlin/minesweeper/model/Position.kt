package minesweeper.model

@JvmInline
value class Position private constructor(
    val position: Int
) {

    fun isGreaterThan(other: Position): Boolean = position > other.position

    fun isLessThan(other: Position): Boolean = position < other.position

    operator fun plus(other: Int): Position = from(position + other)

    companion object {
        private const val MIN_POSITION = 0

        fun from(position: Int): Position = Position(position.coerceAtLeast(MIN_POSITION))
    }
}
