package domain

object RandomMinesweeperFactory : MinesweeperFactory() {
    override fun getMinePositions(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
        val cellCount = minesweeperInfo.rowCount * minesweeperInfo.columnCount
        val mineIndexes = cellCount.toShuffledIndexes().take(minesweeperInfo.mineCount)

        return mineIndexes.map { mineIndex ->
            CellPosition.from(mineIndex, minesweeperInfo.columnCount)
        }
    }

    private fun Int.toShuffledIndexes(): List<Int> {
        return (0 until this.coerceAtLeast(1)).shuffled()
    }
}
