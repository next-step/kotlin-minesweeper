package minesweeper.domain

sealed class Spot(val coordinate: Coordinate) {
    abstract fun isMine(): Boolean
}

class SafeSpot(coordinate: Coordinate) : Spot(coordinate) {
    var nearbyMineCount: Int = 0

    override fun isMine(): Boolean {
        return false
    }

    fun updateNearbyMineCount(count: Int) {
        nearbyMineCount = count
    }
}

class MineSpot(coordinate: Coordinate) : Spot(coordinate) {
    override fun isMine(): Boolean {
        return true
    }
}
