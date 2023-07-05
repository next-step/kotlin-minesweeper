package minesweeper.domain

class Minesweeper(
    val minesweeperArray: Array<IntArray>,
    val minePositions: MinePositions
) {
    init {
        for (minePosition in minePositions.minePositions) {
            minesweeperArray[minePosition.rowPosition][minePosition.colPosition] = -1
        }

        val mineCount = MineCount(minesweeperArray)
        mineCount.initMineCount()
    }

    companion object {
        fun from(rows: Rows, cols: Cols, mine: MineValue): Minesweeper {
            val rowsValue = rows.value
            val colsValue = cols.value
            return Minesweeper(Array(rowsValue) { IntArray(colsValue) }, MinePositions.from(rows, cols, mine))
        }
    }
}
