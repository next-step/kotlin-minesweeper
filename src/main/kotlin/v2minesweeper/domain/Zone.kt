package v2minesweeper.domain

sealed class Zone(
    var isHidden: Boolean
) {
    fun open() {
        isHidden = false
    }
}

private const val INIT_HIDDEN = true

class MineZone(
    isHidden: Boolean = INIT_HIDDEN
) : Zone(
    isHidden = isHidden
)

class SafeZone(
    isHidden: Boolean = INIT_HIDDEN
) : Zone(
    isHidden = isHidden
)
