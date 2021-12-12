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
        val openPositions = getOpenPositions(targetPosition)
        return cells.mapValues { (position, cell) ->
            if (position in openPositions) {
                cell.tryOpen()
            } else {
                cell
            }
        }.let(::Cells)
    }

    private fun getOpenPositions(targetPosition: Position): List<Position> {
        val visit = cells.mapValues { false }.toMutableMap()
        return getOpenPositions(visit, targetPosition)
    }

    private fun getOpenPositions(visit: MutableMap<Position, Boolean>, position: Position): List<Position> {
        if (visit[position] == true) {
            return emptyList()
        }
        visit[position] = true

        val cell = cells[position] ?: return emptyList()
        val positions = mutableListOf<Position>()
        if (cell.isZero) {
            val directions = position.asDirections()
                .asSequence()
                .filter { visit[it] == false }
                .flatMap { getOpenPositions(visit, it) }
            positions.addAll(directions.toList())
        }
        if (!cell.isVisible) {
            positions.add(position)
        }
        return positions
    }

    fun isAllOpenedWithoutMine(): Boolean = cells
        .filterValues { !it.isMine }
        .all { (_, cell) -> cell.isVisible }

    companion object {
        val EMPTY: Cells = Cells(emptyList())
    }
}
