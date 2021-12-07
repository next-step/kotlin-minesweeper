package minesweeper.model

class Cells(cells: Map<Position, Cell>) {

    private val cells = cells.toMap()

    constructor(cells: List<Cell>) : this(cells.associateBy { it.position })

    fun maxColumnOrNull(): Column? = cells.maxByOrNull { (position, _) -> position.column.value }
        ?.value
        ?.column

    fun maxRowOrNull(): Row? = cells.maxByOrNull { (position, _) -> position.row.value }
        ?.value
        ?.row

    operator fun get(position: Position): Cell? = cells[position]

    fun isMine(position: Position): Boolean = get(position)?.isMine == true

    fun mine(position: Position): Cells {
        val cell = get(position) ?: return this
        return Cells(cells.toMutableMap() + mapOf(position to cell.mine()))
    }

    fun incrementAll(positions: List<Position>): Cells {
        val cells = cells.toMutableMap()
        positions.forEach { position ->
            val cell = cells[position] ?: return@forEach
            cells[position] = cell.increment()
        }
        return Cells(cells)
    }

    fun tryOpen(targetPosition: Position): Cells {
        if (!cells.containsKey(targetPosition)) {
            return this
        }
        return Cells(tryOpen(cells, targetPosition))
    }

    private fun tryOpen(cells: Map<Position, Cell>, targetPosition: Position): Map<Position, Cell> {
        val positions = mutableSetOf(targetPosition)
        val result = cells.toMutableMap()
        while (positions.isNotEmpty()) {
            val position = positions.first()
            val cell = result[position] ?: break
            if (cell.isZero) {
                position.asDirections()
                    .filter { result[it]?.isVisible == false }
                    .let(positions::addAll)
            }
            result[position] = cell.tryOpen()
            positions.remove(position)
        }
        return result
    }

    fun isAllOpenedWithoutMine(): Boolean = cells
        .filterValues { !it.isMine }
        .all { (_, cell) -> cell.isVisible }

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
