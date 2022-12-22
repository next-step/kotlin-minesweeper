package domain

data class Position private constructor(
    val x: Int,
    val y: Int
) : Comparable<Position> {

    override fun compareTo(other: Position): Int {
        return when {
            this.x != other.x -> this.x compareTo other.x
            this.y != other.y -> this.y compareTo other.y
            else -> 0
        }
    }

    fun surroundings(): List<Position> {
        return (UP..DOWN).flatMap { x ->
            (LEFT..RIGHT).map { y -> of(this.x + x, this.y + y) }
        } - this
    }

    companion object {
        const val POSITION_START = 0
        private const val UP = -1
        private const val DOWN = 1
        private const val LEFT = -1
        private const val RIGHT = 1

        private val cache = mutableMapOf<Pair<Int, Int>, Position>()

        fun of(x: Int, y: Int): Position {
            val position = x to y
            return cache.computeIfAbsent(position) { Position(x, y) }
        }
    }
}
