package minesweeper.domain

data class MineCount(
    private val minesweeperArray: Array<IntArray>,
) {

    fun initMineCount() {
        val rows = minesweeperArray.size
        val cols = minesweeperArray[0].size

        (0 until rows).forEach {
            (0 until cols).forEach { col ->
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

        return minesweeperArray.contentDeepEquals(other.minesweeperArray)
    }

    override fun hashCode(): Int {
        return minesweeperArray.contentDeepHashCode()
    }

    override fun toString(): String {
        return "MineCount(minesweeperArray=${minesweeperArray.contentToString()})"
    }
}
