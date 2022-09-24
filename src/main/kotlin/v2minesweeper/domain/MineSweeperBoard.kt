package v2minesweeper.domain

class MineSweeperBoard(
    val zones: Zones,
    val mineNumberInfos: Map<Position, Int>
) {
    constructor(zones: Zones) : this(zones, zones.findAllNearMineNumber())

    fun isPlaying(): Boolean {
        return zones.isNotOpenMineZone() && zones.existHiddenSafeZone()
    }

    fun open(position: Position) {
        zones.open(position)
        if (mineNumberInfos[position] == NO_MINE) {
            zones.getNearSafeZonesByPosition(position)
                .forEach { open(it) }
        }
    }

    operator fun get(position: Position): Zone {
        return zones[position]
    }

    fun getResult(): GameResult {
        if (!zones.isNotOpenMineZone()) {
            return GameResult.LOSE
        }

        return GameResult.WIN
    }

    companion object {
        private const val NO_MINE = 0
    }
}
