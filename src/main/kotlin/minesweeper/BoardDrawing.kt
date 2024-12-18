package minesweeper

data class BoardDrawing(val values: MutableList<List<CellType>>) {
    fun hasNext(): Boolean {
        return values.isNotEmpty()
    }

    fun next(): List<CellType> {
        return values.removeFirst()
    }

    companion object {
        fun create(cells: Cells): BoardDrawing {
            val result = mutableListOf<List<CellType>>()
            val cellSize = cells.rowSize()
            while (cellSize > result.size) {
                val row = cells.rowAt(result.size) ?: break

                result.add(row)
            }

            return BoardDrawing(result)
        }
    }
}
