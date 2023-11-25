class GameBoard(cellMatrix: List<CellGrid>) {
    private var _cellMatrix: MutableList<CellGrid> = cellMatrix.toMutableList()
    val cellMatrix: List<CellGrid>
        get() = _cellMatrix.toList()

    fun plantMines(points: List<Point>) = points.forEach { _cellMatrix[it.x] = _cellMatrix[it.x].plantMine(it.y) }
    fun calculateMineCount(): List<List<Int>> {
        val mineCountMatrix = MutableList(cellMatrix.size) { MutableList(cellMatrix[0].size) { 0 } }
        cellMatrix.forEachIndexed { x, cellGrid ->
            cellGrid.cells.forEachIndexed { y, cell ->
                if (cell.isMine()) {
                    mineCountMatrix[x][y] = -1
                    for (point in POINT_LIST) {
                        if (x + point.x !in cellMatrix.indices || y + point.y !in cellMatrix[0].cells.indices) continue
                        if (mineCountMatrix[x + point.x][y + point.y] == -1) continue
                        mineCountMatrix[x + point.x][y + point.y] += 1
                    }
                }
            }
        }
        return mineCountMatrix
    }

    companion object {
        val POINT_LIST = listOf(
            Point(0, 1),
            Point(1, 1),
            Point(1, 0),
            Point(1, -1),
            Point(0, -1),
            Point(-1, -1),
            Point(-1, 0),
            Point(-1, 1)
        )

        fun of(height: Int, width: Int): GameBoard = GameBoard((0 until height).map { CellGrid.of(width) })
    }
}
