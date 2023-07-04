package minesweeper.domain

class Minesweeper(rows: Rows, cols: Cols, mine: MineValue) {
    val minesweeperArray: Array<IntArray>
    private val minePositions: MinePositions

    init {
        val rowsValue = rows.value
        val colsValue = cols.value

        minesweeperArray = Array(rowsValue) { IntArray(colsValue) }
        minePositions = MinePositions(rowsValue, colsValue, mine.value)

        for (minePosition in minePositions.minePositions) {
            minesweeperArray[minePosition.rowPosition][minePosition.colPosition] = -1
        }
        val mineCount = MineCount(minesweeperArray, rows, cols)
        mineCount.initMineCount()
    }
}
