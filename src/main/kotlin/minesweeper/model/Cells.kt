package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { it.row.value }?.row

    fun mineCount(): MineCount = MineCount.valueOf(cells.count { it is Cell.Mine })

    operator fun get(position: Position): Cell? = cells.find { it.row == position.row && it.column == position.column }

    fun mine(position: Position): Cells = cells.map { cell ->
        if (cell.position == position) {
            cell.mine()
        } else {
            cell
        }
    }.let(::Cells)

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
