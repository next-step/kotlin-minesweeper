package model

class RandomMineStrategy: MineStrategy {
    override fun isMineIterator(totalCellCount: Int, mineCount: Int): Iterator<Boolean> {
        return IntRange(0, totalCellCount - 1).map {
            it < mineCount
        }.shuffled().iterator()
    }
}