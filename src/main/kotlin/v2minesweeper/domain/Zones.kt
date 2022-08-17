package v2minesweeper.domain

@JvmInline
value class Zones(
    val values: Map<Position, Zone>
) {
    fun findAllNearMineNumber(): Map<Position, Int> {
        return values.keys.associateWith { getNearMineNumberByPosition(it) }
    }

    private fun getNearMineNumberByPosition(position: Position): Int {
        return position.toNextPositions().count { values[it] is MineZone }
    }
}

fun Map<Pair<Int, Int>, Zone>.toZones(): Zones = Zones(this.mapKeys { Position(it.key) })
