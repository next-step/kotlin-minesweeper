package minesweeper.domain

class SafeSpot(coordinate: Coordinate) : Spot(coordinate) {
    var nearbyMineCount: Int = 0

    override fun isMine(): Boolean {
        return false
    }

    fun updateNearbyMineCount(count: Int) {
        nearbyMineCount = count
    }
}
