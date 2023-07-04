package minesweeper.domain

data class MineCount(
    private val minesweeperArray: Array<IntArray>,
    private val rows: Rows,
    private val cols: Cols
) {

    fun initMineCount() {
        (0 until rows.value).forEach {
            (0 until cols.value).forEach { col ->
                addMineCount(it, col)
            }
        }
    }

    fun positionMineCount(i: Int, j: Int): Int {
        return minesweeperArray[i][j]
    }

    private fun addMineCount(i: Int, j: Int) {
        if (minesweeperArray[i][j] != -1) {
            minesweeperArray[i][j] = calculateMineCount(Pair(i, j))
        }
    }

    private fun calculateMineCount(currentPosition: Pair<Int, Int>): Int {
        var mineCount = 0
        val (row, col) = currentPosition
        val findPositions = FindPosition.positions(row, col)

        findPositions
            .filter { isMineExists(it) }
            .forEach { _ -> mineCount += 1 }

        return mineCount
    }

    private fun isMineExists(findMinePosition: Pair<Int, Int>): Boolean {
        val (row, col) = findMinePosition
        val rowSize = minesweeperArray.size
        val colSize = minesweeperArray[0].size

        if (row == -1 || col == -1 || row >= rowSize || col >= colSize) {
            return false
        }

        return minesweeperArray[row][col] == -1
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MineCount

        if (!minesweeperArray.contentDeepEquals(other.minesweeperArray)) return false
        if (rows != other.rows) return false
        return cols == other.cols
    }

    override fun hashCode(): Int {
        var result = minesweeperArray.contentDeepHashCode()
        result = 31 * result + rows.hashCode()
        result = 31 * result + cols.hashCode()
        return result
    }
}
