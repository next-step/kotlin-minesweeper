package minesweeper.domain

sealed class Zone(
    var isHidden: Boolean = true
) {
    fun open() {
        if (!isHidden) {
            return
        }

        isHidden = false
    }

    abstract fun isSafeZone(): Boolean

    abstract fun isMineZone(): Boolean

    fun isOpenable(): Boolean = isSafeZone() && isHidden
}

class MineZone(
    isHidden: Boolean = true
) : Zone(isHidden) {
    override fun isSafeZone(): Boolean = false

    override fun isMineZone(): Boolean = true
}

class SafeZone(
    isHidden: Boolean = true
) : Zone(isHidden) {
    override fun isSafeZone(): Boolean = true

    override fun isMineZone(): Boolean = false
}
