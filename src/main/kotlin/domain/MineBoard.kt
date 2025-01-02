package domain

import constants.MineSweeperConstants.MINIMUM_HEIGHT
import constants.MineSweeperConstants.MINIMUM_WIDTH

class MineBoard(private val cells: Cells) {
    fun cellsSize() = cells.numberOfTotalCells()

    fun hasMineExploded(): Boolean {
        return cells.isAnyMineCellOpened()
    }

    fun isCleared(): Boolean {
        return cells.isAllEmptyCellsOpened()
    }

    fun getCell(coordinate: Coordinate): Cell = cells.get(coordinate)

    fun openCell(coordinate: Coordinate) = cells.get(coordinate).open()

    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0

        for (direction in Direction.entries) {
            val nextCoordinate = cell.coordinate + direction.offset
            if (isOutOfMineBoard(nextCoordinate)) {
                continue
            }

            if (cells.get(nextCoordinate).isMineCell()) {
                numberOfMines++
            }
        }
        return numberOfMines
    }

    fun getAdjacentCoordinates(cell: Cell): List<Coordinate> {
        val adjacentCoordinates = mutableListOf<Coordinate>()

        for (direction in Direction.entries) {
            val nextCoordinate = cell.coordinate + direction.offset
            if (!isOutOfMineBoard(nextCoordinate)) {
                adjacentCoordinates.add(nextCoordinate)
            }
        }

        return adjacentCoordinates
    }

    private fun isOutOfMineBoard(coordinate: Coordinate): Boolean {
        return (
            Row(MINIMUM_HEIGHT) <= coordinate.row && coordinate.row <= cells.height &&
                Col(MINIMUM_WIDTH) <= coordinate.col && coordinate.col <= cells.width
        ).not()
    }

    fun cellsByRow(): Map<Row, List<Cell>> {
        return cells.groupByRow()
    }
}
