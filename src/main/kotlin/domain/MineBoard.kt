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

    fun isMineCell(coordinate: Coordinate): Boolean {
        return cells.get(coordinate).isMineCell()
    }
}
