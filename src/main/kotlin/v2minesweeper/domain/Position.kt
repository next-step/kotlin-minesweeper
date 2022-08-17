package v2minesweeper.domain

private const val INIT_POSITION = 1
private val NEAR_POSITIONS = arrayOf(
    Position(-1 to -1),
    Position(-1 to 0),
    Position(-1 to 1),
    Position(0 to -1),
    Position(0 to 1),
    Position(1 to -1),
    Position(1 to 0),
    Position(1 to 1)
)

@JvmInline
value class Position(
    val value: Pair<Int, Int>
) {
    fun toNextPositions(): List<Position> {
        return NEAR_POSITIONS.map { it + this }
            .filter(Position::isValidPosition)
    }

    private fun isValidPosition(): Boolean {
        return value.first >= INIT_POSITION && value.second >= INIT_POSITION
    }

    operator fun plus(other: Position) = Position(this.value + other.value)
}

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
    (this.first + other.first) to (this.second + other.second)

operator fun <V> Map<Position, V>.get(position: Pair<Int, Int>): V {
    return this[Position(position)] ?: throw IllegalArgumentException("존재하지 않는 위치입니다.")
}
