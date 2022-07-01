package domain

object RandomMinesweeperFactory : MinesweeperFactory {
    override fun create(minesweeperInfo: MinesweeperInfo): Minesweeper {
        val matrix: Array<Array<Cell>> = Array(minesweeperInfo.rowCount) {
            Array(minesweeperInfo.columnCount) {
                Cell.Land.ZERO
            }
        }

        getMinePositions(minesweeperInfo).forEach { minePosition ->
            matrix[minePosition.row][minePosition.column] = Cell.Mine

            Direction.values().forEach { direction ->
                val row = minePosition.row + direction.diffRow
                val column = minePosition.column + direction.diffColumn

                if (row < 0 || column < 0 || row >= minesweeperInfo.rowCount || column >= minesweeperInfo.columnCount) return@forEach
                matrix[row][column] = matrix[row][column].increaseMineCount()
            }
        }

        return Minesweeper(
            matrix.map { row -> Row(*row) }
        )
    }

    private fun getMinePositions(minesweeperInfo: MinesweeperInfo): List<CellPosition> {
        val cellCount = minesweeperInfo.rowCount * minesweeperInfo.columnCount
        val mineIndexes = cellCount.toShuffledIndexes().take(minesweeperInfo.mineCount)

        return mineIndexes.map { mineIndex ->
            CellPosition.from(mineIndex, minesweeperInfo.columnCount)
        }
    }

    private fun Int.toShuffledIndexes(): List<Int> {
        return (0 until this.coerceAtLeast(1)).shuffled()
    }

    private fun getLandCount(minesweeperInfo: MinesweeperInfo): Int {
        val cellCount = minesweeperInfo.rowCount * minesweeperInfo.columnCount
        return (cellCount - minesweeperInfo.mineCount).coerceAtLeast(0)
    }
}
