package minesweeper

data class DrawingCell(val position: Position, val isMine: Boolean, val neighborsMineCount: Int)

data class DrawingRow(val cells: List<DrawingCell>) : List<DrawingCell> by cells

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
        DrawingRow(rowCells.map { cell ->
            DrawingCell(cell.position, cell.isMine, cell.mineCount)
        })
    }.toMutableList()
}
