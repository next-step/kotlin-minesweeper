package domain

class Row(private val cells: List<Cell>) {
    fun getCells(): List<Cell> = cells.toList()

    fun addNumberHints(
        rowIndex: Int,
        allCells: Cells,
    ): Row {
        val updatedCells =
            cells.mapIndexed { colIndex, cell ->
                cell.addNumberHint(rowIndex, colIndex, allCells)
            }
        return Row(updatedCells)
    }

    operator fun get(column: Int): Cell = cells[column]

    val size: Int
        get() = cells.size

    companion object {
        fun create(
            width: Int,
            cellFactory: (Int) -> Cell,
        ): Row {
            val cells = List(width) { col -> cellFactory(col) }
            return Row(cells)
        }
    }
}
