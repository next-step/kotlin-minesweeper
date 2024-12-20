package minesweeper

sealed class DrawingCell {
    data object CloseCell : DrawingCell()

    data class OpenCell(val cellValue: Int) : DrawingCell()

    data object MineCell : DrawingCell()
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
    return values.values
        .groupBy { it.y }
        .map { (_, cells) ->
            DrawingRow(
                cells.map { cell ->
                    if (cell.isOpen) {
                        DrawingCell.OpenCell(cell.neighborMineCount)
                    } else {
                        DrawingCell.CloseCell
                    }
                },
            )
        }
        .toMutableList()
}
