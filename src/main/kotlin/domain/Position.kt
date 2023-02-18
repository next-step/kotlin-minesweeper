package domain

data class Position private constructor(
    val x: Int,
    val y: Int
) : Comparable<Position> {

    fun top(): Position = copy(y = y.plus(1))

    fun topLeft(): Position = copy(x = x.minus(1), y = y.plus(1))

    fun topRight(): Position = copy(x = x.plus(1), y = y.plus(1))

    fun left(): Position = copy(x = x.minus(1))

    fun right(): Position = copy(x = x.plus(1))

    fun bottom(): Position = copy(y = y.minus(1))

    fun bottomLeft(): Position = copy(x = x.minus(1), y = y.minus(1))

    fun bottomRight(): Position = copy(x = x.plus(1), y = y.minus(1))

    override fun compareTo(other: Position): Int {
        return when {
            this.x != other.x -> this.x compareTo other.x
            this.y != other.y -> this.y compareTo other.y
            else -> 0
        }
    }

    fun surroundings(): List<Position> {
        return listOf(top(), topLeft(), topRight(), left(), right(), bottom(), bottomLeft(), bottomRight())
    }

    companion object {
        const val POSITION_START = 1

        private val cache = mutableMapOf<Pair<Int, Int>, Position>()

        fun of(x: Int, y: Int): Position {
            val position = x to y
            return cache.computeIfAbsent(position) { Position(x, y) }
        }
    }
}
