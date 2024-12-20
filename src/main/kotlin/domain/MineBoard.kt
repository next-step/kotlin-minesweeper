package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0
        for (i in DIRECTION_START_COUNT until DIRECTION_COUNT) {
            val nextRow = ROW_OFFSETS[i] + cell.coordinate.row.value
            val nextCol = COL_OFFSETS[i] + cell.coordinate.col.value
            if (MINIMUM_HEIGHT > nextRow || nextRow > mineGameMetric.boardHeightSize ||
                MINIMUM_WIDTH > nextCol || nextCol > mineGameMetric.boardWidthSize
            ) {
                continue
            }

            val nextCoordinate = cells.getCoordinateIs(Coordinate(Row(nextRow), Col(nextCol)))

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
