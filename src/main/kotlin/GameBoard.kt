class GameBoard(cellMatrix: List<CellGrid>) {
    private var _cellMatrix: MutableList<CellGrid> = cellMatrix.toMutableList()
    val cellMatrix: List<CellGrid>
        get() = _cellMatrix.toList()

    fun plantMines(points: List<Point>) = points.forEach { _cellMatrix[it.x] = _cellMatrix[it.x].plantMine(it.y) }

    companion object {
        fun of(height: Int, width: Int): GameBoard = GameBoard((0 until height).map { CellGrid.of(width) })
    }
}
