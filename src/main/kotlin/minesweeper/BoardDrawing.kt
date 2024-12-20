package minesweeper

sealed class DrawingCell {
    data object MineCell : DrawingCell()
    data class NumberCell(val mineCount: Int) : DrawingCell()
}

data class DrawingRow(val cells: List<DrawingCell>) {
    fun forEach(action: (DrawingCell) -> Unit) {
        cells.forEach(action)
    }
}

data class BoardDrawing(private val _values: MutableList<DrawingRow>) {
    fun hasNext(): Boolean = _values.isNotEmpty()

    fun next(): DrawingRow = _values.removeFirst()

    companion object {
        fun create(cells: Cells): BoardDrawing {
            return BoardDrawing(cells.toDrawingRow())
        }
    }
}

private fun Cells.toDrawingRow(): MutableList<DrawingRow> {
    return (0 until rowSize()).map { i ->
        val rowCells = rowAt(i)
        DrawingRow(
            rowCells.map { cell ->
                when (cell) {
                    is Cell.MineCell -> DrawingCell.MineCell
                    is Cell.NumberCell -> DrawingCell.NumberCell(cell.neighborMineCount)
                }
            },
        )
    }.toMutableList()
}
