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

    fun getResult(): MineSweeperResult {
        if (isPlaying) {
            throw IllegalStateException("지뢰찾기 게임이 진행중입니다.")
        }

        return when (zones.isAllHiddenMineZone()) {
            true -> MineSweeperResult.WIN
            false -> MineSweeperResult.LOSE
        }
    }
}
