package minesweepertest

interface Block {
    fun getNearbyMineCount(): Int
    fun setNearbyMineCount(mineCount: Int)
}
