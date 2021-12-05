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
        return Cells(toMutableMap() + mapOf(position to cell.mine()))
    }

    fun incrementAll(positions: List<Position>): Cells {
        val result = toMutableMap()
        positions.forEach { position ->
            val cell = result[position] ?: return@forEach
            result[position] = cell.increment()
        }
        return Cells(result)
    }

    fun tryOpen(targetPosition: Position): Cells {
        if (!cells.containsKey(targetPosition)) {
            return this
        }
        return Cells(cells.tryOpen(targetPosition))
    }

    private fun Map<Position, Cell>.tryOpen(targetPosition: Position): Map<Position, Cell> {
        val positions = mutableSetOf(targetPosition)
        val result = toMutableMap()
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

    fun isAllOpened(): Boolean = cells.all { (_, cell) -> cell.isVisible }

    private fun toMutableMap(): MutableMap<Position, Cell> = cells.toMutableMap()

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
