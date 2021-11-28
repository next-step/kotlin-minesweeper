package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { it.row.value }?.row

    fun mineCount(): MineCount = MineCount.valueOf(cells.count { it is Cell.Mine })

    operator fun get(position: Position): Cell? = cells.find { it.row == position.row && it.column == position.column }

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
