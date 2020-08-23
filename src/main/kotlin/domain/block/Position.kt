package domain.block

class Position private constructor(
    private val x: Int,
    private val y: Int
) : Comparable<Position> {

    fun surroundings(): List<Position> {
        return Direction.values().map { it.transform(this) }
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

    private enum class Direction(
        val transform: (Position) -> Position
    ) {
        EAST({ of(it.x + 1, it.y) }),
        WEST({ of(it.x - 1, it.y) }),
        NORTH({ of(it.x, it.y - 1) }),
        SOUTH({ of(it.x, it.y + 1) }),
        NORTH_WEST({ of(it.x - 1, it.y - 1) }),
        NORTH_EAST({ of(it.x + 1, it.y - 1) }),
        SOUTH_EAST({ of(it.x + 1, it.y + 1) }),
        SOUTH_WEST({ of(it.x - 1, it.y + 1) });
    }

    companion object {
        const val POSITION_START = 1

        private val cache = mutableMapOf<Pair<Int, Int>, Position>()

        fun of(x: Int, y: Int): Position {
            return cache.computeIfAbsent(x to y) { Position(x, y) }
        }

        fun of(pair: Pair<Int, Int>): Position {
            return of(pair.first, pair.second)
        }
    }
}
