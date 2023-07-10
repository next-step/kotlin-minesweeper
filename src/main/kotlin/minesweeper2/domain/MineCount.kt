package minesweeper2.domain

import minesweeper2.model.PositionLocation

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
            positions.updatePositionValue(i, j, calculateMineCount(PositionLocation(i, j)))
        }
    }

    private fun calculateMineCount(currentPosition: PositionLocation): Int {
        var mineCount = 0
        val findPositions = FindPosition.positionLocations(currentPosition, positions.rows, positions.cols)

        findPositions
            .filter { isMineExists(it) }
            .forEach { _ -> mineCount += 1 }

        return Position(mineCount).value
    }

    private fun isMineExists(findMinePosition: PositionLocation): Boolean {
        return positions.position(findMinePosition.row, findMinePosition.col).value == -1
    }
}
