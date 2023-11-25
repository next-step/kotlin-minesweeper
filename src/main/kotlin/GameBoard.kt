class GameBoard(cellMatrix: List<CellGrid>) {
    private var _cellMatrix: MutableList<CellGrid> = cellMatrix.toMutableList()
    val cellMatrix: List<CellGrid>
        get() = _cellMatrix.toList()
    private val height: Int
        get() = _cellMatrix.size
    private val width: Int
        get() = _cellMatrix[Const.FIRST_INDEX].size

    fun plantMines(points: List<Point>) = points.forEach { _cellMatrix[it.x] = _cellMatrix[it.x].plantMine(it.y) }
    fun calculateMineCount(): MinefieldMatrix {
        var result = MinefieldMatrix.of(height, width)
        cellMatrix.forEachIndexed { height, cellGrid ->
            result = cellGrid.updateMineCountsForRow(result, height)
        }
        return result
    }

    companion object {
        fun of(height: Int, width: Int): GameBoard =
            GameBoard((Const.START_INDEX until height).map { CellGrid.of(width) })
    }
}
