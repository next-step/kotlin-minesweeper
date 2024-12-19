package domain

class Grid(
    private val height: Height,
    private val width: Width,
    private val  cells: Cells,
) {
    private val rowCount: Int = height.value
    private val columnCount: Int = width.value

    fun toDisplayRows(cellMapper: CellMapper): List<String> =
        cells.map { row ->
            row.joinToString(" ") { it.mapToDisplay(cellMapper) }
        }

    fun withNumberHints(): Grid {
        val updatedCells = cells.addNumberHints()
        return Grid(height, width, updatedCells)
    }
}
