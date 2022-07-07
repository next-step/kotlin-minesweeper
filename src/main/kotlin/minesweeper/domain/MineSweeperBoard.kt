package minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Map<Position, Zone>,
) {
    fun openAllZone(): Map<Position, Int> {
        return zones.keys.associateWith { countOfNearMine(it) }
    }

    private fun countOfNearMine(position: Position): Int {
        return position.getNearPositions()
            .count { zones[it] is MineZone }
    }
}
