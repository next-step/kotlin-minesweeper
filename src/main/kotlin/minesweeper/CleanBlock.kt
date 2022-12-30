package minesweeper

class CleanBlock(
    private var _nearbyMineCount: Int = 0
) : Block {
    override fun getNearbyMineCount(): Int {
        return _nearbyMineCount
    }

    override fun setNearbyMineCount(mineCount: Int) {
        _nearbyMineCount = mineCount
    }
}
