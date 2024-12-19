package domain

class MineBoard(
    private val mineGameMetric: MineGameMetric,
    private val cells: Cells,
) {
    fun countAdjacentMines(cell: Cell): Int {
        var numberOfMines = 0
        val dr = arrayOf(-1, 1, 0, 0, -1, -1, 1, 1)
        val dc = arrayOf(0, 0, -1, 1, -1, 1, -1, 1)

        for (i in dr.indices) {
            val nr = dr[i] + cell.coordinate.r.value
            val nc = dc[i] + cell.coordinate.c.value
            if (0 > nr || nr > mineGameMetric.boardHeightSize || 0 > nc || nc > mineGameMetric.boardWidthSize) {
                continue
            }

            val nextCoordinate = cells.getCoordinateIs(Coordinate(Row(nr), Col(nc)))

            if (nextCoordinate.isMineCell()) {
                numberOfMines++
            }
        }

        return numberOfMines
    }
}
