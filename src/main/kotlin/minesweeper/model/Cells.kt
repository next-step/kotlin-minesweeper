package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { it.row.value }?.row

    operator fun get(position: Position): Cell? = cells.find { it.row == position.row && it.column == position.column }

    fun mine(position: Position): Cells = update(
        predicate = { it.position == position },
        transform = { it.mine() }
    )

    fun increment(position: Position): Cells = update(
        predicate = { it.position == position },
        transform = { it.increment() }
    )

    fun incrementAll(positions: List<Position>): Cells = update(
        predicate = { it.position in positions },
        transform = { it.increment() }
    )

    fun tryOpen(position: Position): Cells = update(
        predicate = { it.position == position },
        transform = { it.tryOpen() }
    )

    private fun update(
        predicate: (Cell) -> Boolean,
        transform: (Cell) -> (Cell)
    ): Cells = cells
        .map { cell -> if (predicate(cell)) transform(cell) else cell }
        .let(::Cells)

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
