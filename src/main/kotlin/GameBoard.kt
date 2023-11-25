class GameBoard(cellMatrix: List<CellGrid>) {
    private var _cellMatrix: MutableList<CellGrid> = cellMatrix.toMutableList()
    private var _mines: Mines = Mines(emptyList())
    val cellMatrix: List<CellGrid>
        get() = _cellMatrix.toList()
    private val height: Int
        get() = _cellMatrix.size
    private val width: Int
        get() = _cellMatrix[Const.FIRST_INDEX].size
    val mines: Mines
        get() = _mines

    fun plantMines(points: List<Point>) = points.forEach { _cellMatrix[it.x] = _cellMatrix[it.x].plantMine(it.y) }
    fun plantMines(targetMines: Mines) {
        _mines = targetMines
    }

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
