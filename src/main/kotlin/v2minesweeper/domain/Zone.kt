package v2minesweeper.domain

sealed class Zone(
    val isHidden: Boolean
)

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
