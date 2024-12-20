package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0

        for (direction in Direction.entries) {
            val nextRow = cell.coordinate.row.value + direction.rowOffset
            val nextCol = cell.coordinate.col.value + direction.colOffset

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
}
