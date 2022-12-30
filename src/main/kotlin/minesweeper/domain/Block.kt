package minesweeper.domain

sealed interface Block {
    fun getNearbyMineCount(): Int
    fun setNearbyMineCount(mineCount: Int)
}

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

class MineBlock(
    private var _nearbyMineCount: Int = 0
) : Block {
    override fun setNearbyMineCount(mineCount: Int) {}
    override fun getNearbyMineCount(): Int {
        return _nearbyMineCount
    }
}
