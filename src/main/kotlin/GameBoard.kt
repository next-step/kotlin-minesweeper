class GameBoard(val cellMatrix: List<CellGrid>) {

    fun plantMines(count: Int): GameBoard {
        val size = cellMatrix.size * cellMatrix[0].size
        val minePositions = (0 until size).shuffled().take(count)
        val newCellMatrix = cellMatrix.mapIndexed { rowIndex, cellGrid ->
            CellGrid(cellGrid.cellCollection.mapIndexed { columnIndex, cell ->
                if (minePositions.contains(rowIndex * cellGrid.size + columnIndex)) {
                    cell.copy(value = MineStatus.MINE)
                } else {
                    cell
                }
            })
        }
        return GameBoard(newCellMatrix)
    }

    companion object {
        fun of(height: Int, width: Int): GameBoard = GameBoard((0 until height).map { CellGrid.of(width) })
    }
}
