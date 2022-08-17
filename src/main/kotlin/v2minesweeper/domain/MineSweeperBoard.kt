package v2minesweeper.domain

@JvmInline
value class MineSweeperBoard(
    val zones: Zones
) {
    fun isPlaying(): Boolean {
        return false
    }
}
