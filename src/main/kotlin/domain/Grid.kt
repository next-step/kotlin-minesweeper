package domain

class Grid(
    private val height: Height, 
    private val width: Width, 
    private val cells: List<List<Cell>>,
) {
    val rowCount: Int = height.value
    val columnCount: Int = width.value

    fun getCellAt(row: Int, column: Int): Cell = cells[row][column]

    fun toDisplayRows(cellMapper: CellMapper): List<String> =
        cells.map { row ->
            row.joinToString(" ") { cellMapper.mapToDisplay(it) }
        }

    fun withNumberHints(): Grid {
        val newCells = cells.mapIndexed { rowIndex, row ->
            row.mapIndexed { colIndex, cell ->
                if (cell is Cell.Empty) {
                    val mineCount = countAdjacentMines(rowIndex, colIndex)
                    if (mineCount > 0) Cell.NumberCell(mineCount) else cell
                } else {
                    cell
                }
            }
        }
        return Grid(height, width, newCells)
    }

    private fun countAdjacentMines(row: Int, col: Int): Int {
        val directions = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1,          0 to 1,
            1 to -1,  1 to 0,  1 to 1
        )

        return directions.count { (dr, dc) ->
            val newRow = row + dr
            val newCol = col + dc
            newRow in 0 until rowCount && newCol in 0 until columnCount && getCellAt(newRow, newCol) is Cell.MineCell
        }
    }
}
