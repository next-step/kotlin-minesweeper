package minesweeper.model

@JvmInline
value class Cells(private val cells: List<Cell>) {

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { it.column.value }?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { it.row.value }?.row

    operator fun get(position: Position): Cell? = cells.find { it.row == position.row && it.column == position.column }

    fun isMine(position: Position): Boolean = get(position)?.isMine == true

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

    fun tryOpen(targetPosition: Position): Cells {
        if (get(targetPosition) == null) return this

        val positions = mutableSetOf(targetPosition)
        val map = associateByPosition().toMutableMap()
        while (positions.isNotEmpty()) {
            val position = positions.first()
            val cell = map[position] ?: break
            if (cell.isZero) {
                positions.addAll(position.asDirections())
            }
            map[position] = cell.tryOpen()
            positions.remove(position)
        }
        return Cells(map.values.toList())
    }

    fun isAllOpened(): Boolean = cells.asSequence()
        .filterIsInstance<Cell.Number>()
        .all { it.isVisible }

    private fun associateByPosition(): Map<Position, Cell> = cells.associateBy { it.position }

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
