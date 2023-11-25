class GameBoard(cellMatrix: List<CellGrid>) {
    private var _cellMatrix: MutableList<CellGrid> = cellMatrix.toMutableList()
    val cellMatrix: List<CellGrid>
        get() = _cellMatrix.toList()
    private val height: Int
        get() = _cellMatrix.size
    private val width: Int
        get() = _cellMatrix[0].size
    private val heightIndices: IntRange
        get() = _cellMatrix.indices
    private val widthIndices: IntRange
        get() = _cellMatrix[0].cells.indices

    fun plantMines(points: List<Point>) = points.forEach { _cellMatrix[it.x] = _cellMatrix[it.x].plantMine(it.y) }
    fun calculateMineCount(): List<List<Int>> {
        var result = List(height) { List(width) { 0 } }
        cellMatrix.forEachIndexed { x, cellGrid ->
            result = cellGrid.updateMineCounts(result, x, heightIndices, widthIndices)
        }
        return result
    }

    companion object {
        fun of(height: Int, width: Int): GameBoard = GameBoard((0 until height).map { CellGrid.of(width) })
    }
}
