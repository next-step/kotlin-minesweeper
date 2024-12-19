package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    private val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0
        for (i in DIRECTION_START_COUNT..DIRECTION_COUNT) {
            val nr = ROW_OFFSETS[i] + cell.coordinate.r.value
            val nc = COL_OFFSETS[i] + cell.coordinate.c.value
            if (MINIMUM_HEIGHT > nr || nr > mineGameMetric.boardHeightSize || MINIMUM_WIDTH > nc || nc > mineGameMetric.boardWidthSize) {
                continue
            }

            val nextCoordinate = cells.getCoordinateIs(Coordinate(Row(nr), Col(nc)))

            if (nextCoordinate.isMineCell()) {
                numberOfMines++
            }
        }

        return numberOfMines
    }

    companion object {
        private const val DIRECTION_START_COUNT = 0
        private const val DIRECTION_COUNT = 8
        private val ROW_OFFSETS = arrayOf(-1, 1, 0, 0, -1, -1, 1, 1)
        private val COL_OFFSETS = arrayOf(0, 0, -1, 1, -1, 1, -1, 1)
    }
}
