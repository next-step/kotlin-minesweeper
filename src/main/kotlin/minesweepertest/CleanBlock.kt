package minesweepertest

class CleanBlock(
    private var nearbyMineCount:Int = 0
) {
    fun getValue() = nearbyMineCount
}
