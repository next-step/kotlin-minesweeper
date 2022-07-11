package minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Zones,
) {
    val size: Int
        get() = zones.size

    val isPlaying: Boolean
        get() = zones.isAllHiddenMineZone() && zones.isAnyHiddenSafeZone()

    fun openAllZone(): Map<Position, Int> {
        return zones.openAllZone()
    }

    fun openAt(position: Position) {
        zones.openAt(position)
    }

    operator fun get(position: Position): Zone? = zones[position]
}
