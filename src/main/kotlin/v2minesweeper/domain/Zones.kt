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

    fun isNotOpenMineZone(): Boolean {
        return values.filterValues { it is MineZone && !it.isHidden }
            .isEmpty()
    }

    fun existHiddenSafeZone(): Boolean {
        return values.filterValues { it is SafeZone }
            .values
            .any { it.isHidden }
    }

    fun open(position: Position) {
        values[position]!!.open()
    }

    fun getNearSafeZonesByPosition(position: Position): List<Position> {
        return position.toNextPositions()
            .filter { values[it] is SafeZone && values[it]!!.isHidden }
    }

    operator fun get(position: Position): Zone {
        return values[position] ?: throw IllegalArgumentException("존재하지 않는 위치입니다. position = $position")
    }
}

fun Map<Pair<Int, Int>, Zone>.toZones(): Zones = Zones(this.mapKeys { Position(it.key) })
