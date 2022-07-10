package domain

object RandomMinePositionsFactory : MinePositionsFactory {
    override fun create(minesweeperInfo: MinesweeperInfo): MinePositions {
        val cellCount = minesweeperInfo.rowCount * minesweeperInfo.columnCount
        val mineIndexes = cellCount.toShuffledIndexes().take(minesweeperInfo.mineCount)

        return MinePositions.of(mineIndexes.toMinePositions(minesweeperInfo.columnCount), minesweeperInfo)
    }

    private fun List<Int>.toMinePositions(columnCount: Int): List<CellPosition> {
        return map { mineIndex ->
            getMinePosition(mineIndex, columnCount)
        }
    }

    private fun Int.toShuffledIndexes(): List<Int> {
        return (0 until this.coerceAtLeast(1)).shuffled()
    }

    private fun getMinePosition(index: Int, columnCount: Int): CellPosition {
        val row = index / columnCount
        val column = index % columnCount

        return CellPosition(row, column)
    }
}
