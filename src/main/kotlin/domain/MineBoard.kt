package domain

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0

        for (direction in Direction.entries) {
            val nextCoordinate = cell.coordinate + direction.offset
            if (mineGameMetric.isOutOfMineBoard(nextCoordinate)) {
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
            if (!mineGameMetric.isOutOfMineBoard(nextCoordinate)) {
                adjacentCoordinates.add(nextCoordinate)
            }
        }

        return adjacentCoordinates
    }

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.get(coordinate).isMineCell()
    }

    fun isAnyMineCellOpened(): Boolean {
        return cells.countOpenedMineCells() > 0
    }

    fun isAllEmptyCellsOpened(): Boolean {
        val openedEmptyCellCount = cells.countOpenedEmptyCells()
        val totalEmptyCellCount = cells.countEmptyCells()

        return openedEmptyCellCount == totalEmptyCellCount
    }

    fun openCell(coordinate: Coordinate) {
        cells.get(coordinate).open()
    }

    fun getCell(current: Coordinate): Cell = cells.get(current)
}
