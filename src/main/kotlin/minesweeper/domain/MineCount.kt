package minesweeper.domain

data class MineCount(
    private val positions: Positions,
) {

    fun initMineCount() {
        val rows = positions.rows
        val cols = positions.cols

        (0 until rows).forEach {
            (0 until cols).forEach { col ->
                addMineCount(it, col)
            }
        }
    }

    fun positionMineCount(i: Int, j: Int): Position {
        return positions.position(i, j)
    }

    private fun addMineCount(i: Int, j: Int) {
        if (positions.position(i, j).value != -1) {
            positions.updatePositionValue(i, j, calculateMineCount(Pair(i, j)))
        }
    }

    private fun calculateMineCount(currentPosition: Pair<Int, Int>): Int {
        var mineCount = 0
        val (row, col) = currentPosition
        val findPositions = FindPosition.positions(row, col)

        findPositions
            .filter { isMineExists(it) }
            .forEach { _ -> mineCount += 1 }

        return Position(mineCount).value
    }

    private fun isMineExists(findMinePosition: Pair<Int, Int>): Boolean {
        val (row, col) = findMinePosition
        val rowSize = positions.rows
        val colSize = positions.cols

        if (row == -1 || col == -1 || row >= rowSize || col >= colSize) {
            return false
        }

        return positions.position(row, col).value == -1
    }
}
