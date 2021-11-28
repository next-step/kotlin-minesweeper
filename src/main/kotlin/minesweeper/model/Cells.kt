package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumn(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRow(): Row? = cells.maxByOrNull { it.row.value }?.row

    fun mineCount(): MineCount = MineCount(cells.count { it is Cell.Mine })

    fun get(row: Row, column: Column): Cell? = cells.find { it.row == row && it.column == column }

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
