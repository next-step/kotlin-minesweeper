package minesweeper.domain

@JvmInline
value class Zones(
    val values: Map<Position, Zone>,
) {
    val size: Int
        get() = values.size

    fun openAllZone(): Map<Position, Int> {
        return values.keys.associateWith { countOfNearMine(it) }
    }

    private fun countOfNearMine(position: Position): Int {
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

    fun openAt(position: Position) {
        val selectedZone = values[position] ?: throw IllegalArgumentException("존재하지 않는 칸입니다. 선택한 위치 = $position")
        selectedZone.open()
    }

    operator fun get(position: Position): Zone? {
        return values[position]
    }
}
