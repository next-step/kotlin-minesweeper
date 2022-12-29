package minesweepertest

class CleanBlock(
    private var nearbyMineCount: Int = 0
) : Block {
    fun getValue() = nearbyMineCount
}
