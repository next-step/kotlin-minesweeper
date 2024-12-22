package minesweeper.domain

sealed class Spot(val position: Position) {
    private var isOpened = false

    fun open() {
        require(!isOpened) { "이미 열린 칸입니다." }

        isOpened = true
    }

    fun isOpened(): Boolean {
        return isOpened
    }

    abstract fun isMine(): Boolean
}

class SafeSpot(position: Position, val nearbyMineCount: Int) : Spot(position) {
    override fun isMine(): Boolean {
        return false
    }
}

class MineSpot(position: Position) : Spot(position) {
    override fun isMine(): Boolean {
        return true
    }
}
