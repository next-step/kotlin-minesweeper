package minesweeper.domain

private const val NOT_NEARBY_HAVE_MINE = 0

@JvmInline
value class Zones(
    val values: Map<Position, Zone>,
) {
    val size: Int
        get() = values.size

    fun openAllZone(): Map<Position, Int> {
        return values.keys.associateWith { countNearMines(it) }
    }

    fun openAt(position: Position) {
        val selectedZone = values[position] ?: throw IllegalArgumentException("존재하지 않는 칸입니다. 선택한 위치 = $position")
        selectedZone.open()
        if (selectedZone.isMineZone()) {
            return
        }

        if (countNearMines(position) == NOT_NEARBY_HAVE_MINE) {
            openNearbyPositions(position)
        }
    }

    private fun openNearbyPositions(position: Position) {
        position.getNearPositions()
            .asSequence()
            .filter { values.containsKey(it) }
            .filter { values[it]!!.isOpenable() }
            .forEach { openAt(it) }
    }

    private fun countNearMines(position: Position): Int {
        return position.getNearPositions()
            .count { values[it] is MineZone }
    }

    fun isAllHiddenMineZone(): Boolean {
        return values.values.asSequence()
            .filterIsInstance<MineZone>()
            .all { it.isHidden }
    }

    fun isAnyHiddenSafeZone(): Boolean {
        return values.values.asSequence()
            .filterIsInstance<SafeZone>()
            .any { it.isHidden }
    }

    operator fun get(position: Position): Zone? {
        return values[position]
    }
}
