package domain

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0

        for (direction in Direction.entries) {
            val nextRow = cell.coordinate.row.value + direction.rowOffset
            val nextCol = cell.coordinate.col.value + direction.colOffset

            if (mineGameMetric.isOutOfMineBoard(Coordinate(Row(nextRow), Col(nextCol)))) continue

            val nextCoordinate = cells.getCoordinateIs(Coordinate(Row(nextRow), Col(nextCol)))
            if (nextCoordinate.isMineCell()) {
                numberOfMines++
            }
        }
        return numberOfMines
    }
}
