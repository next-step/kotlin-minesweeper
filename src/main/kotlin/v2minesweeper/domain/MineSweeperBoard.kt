package v2minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Zones
) {
    fun isPlaying(): Boolean {
        return zones.isNotOpenMineZone() && zones.existHiddenSafeZone()
    }

    fun open(position: Position) {
        zones[position].open()
    }

    operator fun get(position: Position): Zone {
        return zones[position]
    }
}
