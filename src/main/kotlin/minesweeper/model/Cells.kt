package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { it.row.value }?.row

    fun mineCount(): MineCount = MineCount.valueOf(cells.count { it is Cell.Mine })

    fun get(row: Row, column: Column): Cell? = cells.find { it.row == row && it.column == column }

    operator fun get(position: Position): Cell? = get(position.row, position.column)

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
