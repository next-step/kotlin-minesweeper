package domain.block

class Position private constructor(
    private val x: Int,
    private val y: Int
) : Comparable<Position> {

    fun surroundings(): List<Position> {
        return (UP..DOWN).flatMap { dy ->
            (LEFT..RIGHT).map { dx ->
                of(
                    this.x + dx,
                    this.y + dy
                )
            }
        } - this
    }

    override fun compareTo(other: Position): Int {
        if (this.y > other.y) return 1
        if (this.y == other.y && this.x > other.x) return 1
        return -1
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "$x, $y"
    }

    companion object {
        const val POSITION_START = 1
        private const val UP = -1
        private const val DOWN = 1
        private const val LEFT = -1
        private const val RIGHT = 1

        private val cache = mutableMapOf<Pair<Int, Int>, Position>()

        fun of(x: Int, y: Int): Position {
            val position = x to y
            return cache.computeIfAbsent(position) {
                Position(
                    x,
                    y
                )
            }
        }
    }
}
