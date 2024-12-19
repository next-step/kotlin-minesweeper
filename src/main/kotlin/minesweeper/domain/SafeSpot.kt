package minesweeper.domain

class SafeSpot(private val y: Int, private val x: Int) : Spot(y, x) {
    var nearbyMineCount: Int = 0

    override fun isMine(): Boolean {
        return false
    }

    fun updateNearbyMineCount(count: Int) {
        nearbyMineCount = count
    }
}
