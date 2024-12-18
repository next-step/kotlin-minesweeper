package domain

class Grid(
    private val height: Height, 
    private val width: Width, 
    private val cells: List<List<Cell>>
) {
    val rowCount: Int = height.value
    val columnCount: Int = width.value

    fun getCellAt(row: Int, column: Int): Cell = cells[row][column]

    fun toDisplayRows(cellMapper: CellMapper): List<String> =
        cells.map { row ->
            row.joinToString(" ") { cellMapper.mapToDisplay(it) }
        }
}