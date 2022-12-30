package minesweeper

class MineBlock(
    private var _nearbyMineCount: Int = 0
) : Block {
    override fun setNearbyMineCount(mineCount: Int) {}
    override fun getNearbyMineCount(): Int {
        return _nearbyMineCount
    }
}
