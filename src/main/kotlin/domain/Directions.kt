package domain

object Directions {
    private val DIRECTIONS =
        listOf(
            -1 to -1,
            -1 to 0,
            -1 to 1,
            0 to -1,
            0 to 1,
            1 to -1,
            1 to 0,
            1 to 1,
        )

    fun countMatching(
        row: Int,
        column: Int,
        cells: Cells,
        predicate: (Cell) -> Boolean,
    ): Int {
        return DIRECTIONS.count { (directionRow, directionColumn) ->
            val newRow = row + directionRow
            val newColumn = column + directionColumn
            newRow in 0 until cells.size &&
                newColumn in 0 until cells[newRow].size &&
                predicate(cells[newRow][newColumn])
        }
    }
}
