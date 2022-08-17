package v2minesweeper.domain

@JvmInline
value class Zones(
    val values: Map<Pair<Int, Int>, Zone>
) {
    fun findAllNearMineNumber(): Map<Pair<Int, Int>, Int> {
        return mapOf()
    }
}

fun Map<Pair<Int, Int>, Zone>.toZones(): Zones = Zones(this)
