package minesweeper.domain

class SafeZone : Zone {
    override fun isMine(): Boolean = false
}
