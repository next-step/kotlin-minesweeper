package minesweeper

interface Block {
    fun getNearbyMineCount(): Int
    fun setNearbyMineCount(mineCount: Int)
}
