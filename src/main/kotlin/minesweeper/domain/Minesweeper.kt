package minesweeper.domain

class Minesweeper(rows: Int, cols: Int, mineCount: Int) {
    val minesweeperArray = Array(rows) { IntArray(cols) }
    private val minePositions = MinePositions(rows, cols, mineCount)

    init {
        for (minePosition in minePositions.minePositions) {
            minesweeperArray[minePosition.rowPosition][minePosition.colPosition] = 1
        }
    }
}
