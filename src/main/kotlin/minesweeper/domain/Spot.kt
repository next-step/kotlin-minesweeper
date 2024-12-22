package minesweeper.domain

sealed class Spot(val position: Position) {
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
