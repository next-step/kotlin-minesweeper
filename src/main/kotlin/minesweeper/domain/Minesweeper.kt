package minesweeper.domain

class Minesweeper(rows: Int, cols: Int, mineCount: Int) {
    val minesweeperArray: Array<IntArray>
    private val minePositions: MinePositions

    init {
        require(cols > 0) { "높이는 0보다 커야 합니다." }
        require(rows > 0) { "너비는 0보다 커야 합니다." }
        require(mineCount > 0) { "지뢰의 수는 0보다 커야 합니다." }
        require(mineCount < cols * rows) { "지뢰의 수는 높이와 너비의 곱보다 작아야 합니다." }

        minesweeperArray = Array(rows) { IntArray(cols) }
        minePositions = MinePositions(rows, cols, mineCount)

        for (minePosition in minePositions.minePositions) {
            minesweeperArray[minePosition.rowPosition][minePosition.colPosition] = -1
        }

        initMineCount(rows, cols)
    }

    private fun initMineCount(rows: Int, cols: Int) {
        (0 until rows).forEach {
            (0 until cols).forEach { col ->
                addMintCount(it, col)
            }
        }
    }

    private fun addMintCount(i: Int, j: Int) {
        if (minesweeperArray[i][j] != -1) {
            minesweeperArray[i][j] = mineCount(minesweeperArray, Pair(i, j))
        }
    }
}
