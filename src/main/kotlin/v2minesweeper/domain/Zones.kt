package v2minesweeper.domain

@JvmInline
value class Zones(
    val values: Map<Pair<Int, Int>, Zone>
) {
    fun findAllNearMineNumber(): Map<Pair<Int, Int>, Int> {
        return values.keys.associateWith { getNearMineNumberByPosition(it) }
    }

    private fun getNearMineNumberByPosition(position: Pair<Int, Int>): Int {
        return position.toNextPositions().count { values[it] is MineZone }
    }
}

fun Map<Pair<Int, Int>, Zone>.toZones(): Zones = Zones(this)

private const val INIT_POSITION = 1
private val NEAR_DIRECTIONS = arrayOf(
    -1 to -1,
    -1 to 0,
    -1 to 1,
    0 to -1,
    0 to 1,
    1 to -1,
    1 to 0,
    1 to 1
)

private fun Pair<Int, Int>.toNextPositions(): List<Pair<Int, Int>> {
    return NEAR_DIRECTIONS.map { it.plus(this) }
        .filter { it.first >= INIT_POSITION && it.second >= INIT_POSITION }
}

private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>) =
    (this.first + other.first) to (this.second + other.second)
