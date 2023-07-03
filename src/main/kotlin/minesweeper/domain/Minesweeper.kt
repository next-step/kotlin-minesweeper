package minesweeper.domain

class Minesweeper(rows: Int, cols: Int, mineCount: Int) {
    val minesweeperArray: Array<IntArray>
    private val minePositions: MinePositions

    init {
        require(cols > 0) { "높이는 0보다 커야 합니다." }
        require(rows > 0) { "너비는 0보다 커야 합니다." }
        require(mineCount > 0) { "지뢰의 수는 0보다 커야 합니다." }

        minesweeperArray = Array(rows) { IntArray(cols) }
        minePositions = MinePositions(rows, cols, mineCount)

        for (minePosition in minePositions.minePositions) {
            minesweeperArray[minePosition.rowPosition][minePosition.colPosition] = 1
        }
    }
}
