package minesweeper

typealias DrawingRow = List<CellType>

data class BoardDrawing(private val _values: MutableList<DrawingRow>) {
    fun hasNext(): Boolean {
        return _values.isNotEmpty()
    }

    fun next(): DrawingRow {
        return _values.removeFirst()
    }

    companion object {
        fun create(cells: Cells): BoardDrawing {
            val result = mutableListOf<DrawingRow>()
            val cellSize = cells.rowSize()
            while (cellSize > result.size) {
                val row = cells.rowAt(result.size) ?: break

                result.add(row)
            }

            return BoardDrawing(result)
        }
    }
}
