package domain

object RandomMinesweeperFactory : MinesweeperFactory {
    override fun create(minesweeperInfo: MinesweeperInfo): Minesweeper {
        val landCount = getLandCount(minesweeperInfo)

        val lands = List(landCount) { Cell.Land.ZERO }
        val mines = List(minesweeperInfo.mineCount) { Cell.Mine }

        val rows = (lands + mines)
            .shuffled()
            .chunked(minesweeperInfo.columnCount)
            .map { cells ->
                Row(cells)
            }

        return Minesweeper(rows)
    }

    private fun getLandCount(minesweeperInfo: MinesweeperInfo): Int {
        val cellCount = minesweeperInfo.rowCount * minesweeperInfo.columnCount
        return (cellCount - minesweeperInfo.mineCount).coerceAtLeast(0)
    }
}
