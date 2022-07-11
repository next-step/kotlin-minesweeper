package minesweeper.domain

sealed class Zone(
    var isHidden: Boolean = true
) {
    fun open() {
        if (!isHidden) {
            throw IllegalArgumentException("이미 open된 칸입니다.")
        }

        isHidden = false
    }
}

class MineZone(
    isHidden: Boolean = true
) : Zone(isHidden)

class SafeZone(
    isHidden: Boolean = true
) : Zone(isHidden)
