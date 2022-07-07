package minesweeper.domain

sealed class Zone(
    val isHidden: Boolean = true
)

class MineZone(
    isHidden: Boolean = true
) : Zone(isHidden)

class SafeZone(
    isHidden: Boolean = true
) : Zone(isHidden)
