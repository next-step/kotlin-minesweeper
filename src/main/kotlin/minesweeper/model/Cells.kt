package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    init {
        require(cells.distinctBy { it.row to it.column } == cells)
    }

    fun maxColumn(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRow(): Row? = cells.maxByOrNull { it.row.value }?.row

    fun get(row: Row, column: Column): Cell? = cells.find { it.row == row && it.column == column }

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
