package minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Map<Position, Zone>,
) {
    val isPlaying: Boolean
        get() = isAllHiddenMineZone() && isAnyHiddenSafeZone()

    fun openAllZone(): Map<Position, Int> {
        return zones.keys.associateWith { countOfNearMine(it) }
    }

    private fun countOfNearMine(position: Position): Int {
        return position.getNearPositions()
            .count { zones[it] is MineZone }
    }

    private fun isAllHiddenMineZone(): Boolean {
        return zones.values.asSequence()
            .filterIsInstance<MineZone>()
            .all { it.isHidden }
    }

    private fun isAnyHiddenSafeZone(): Boolean {
        return zones.values.asSequence()
            .filterIsInstance<SafeZone>()
            .any { it.isHidden }
    }
}
