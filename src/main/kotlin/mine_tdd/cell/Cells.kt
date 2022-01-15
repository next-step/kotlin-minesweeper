package mine_tdd.cell

import mine_tdd.GameStatus
import mine_tdd.cell.Position.Companion.findNearPosition

class Cells(private val cells: List<Cell>) {
    fun values(): List<Cell> = cells
    fun size(): Int = cells.size
    fun getMineCellCount(): Int = cells.filterIsInstance<MineCell>().size
    fun findCell(position: Position): Cell? =
        cells.firstOrNull { it.position.x == position.x && it.position.y == position.y }

    fun getRowCells(row: Int): Cells = cells.filter { it.position.x == row }.sortedBy { it.position.y }.let(::Cells)
    fun getColumnCells(column: Int): Cells = cells.filter { it.position.y == column }.sortedBy { it.position.x }.let(::Cells)
    fun findMineCount(position: Position): Int =
        position.findNearPosition().mapNotNull { findCell(it) }.filterIsInstance<MineCell>().count()

    fun open(cell: Cell?) {
        if (cell is MineCell) return
        cell?.open()
    }

    fun findZeroMineCells(cell: Cell): List<Cell> =
        cell.findNearPosition().mapNotNull { findCell(it) }.filterIsInstance<NoneCell>().filter { it.isZeroMine() }

    fun getZeroMineCellList(position: Position): List<Cell> {
        val cell = findCell(position) ?: return emptyList()
        val list = mutableSetOf(cell)
        var input = setOf(cell)
        do {
            input = searchList(input)
            input = input.filterNot { list.contains(it) }.toSet()
            list.addAll(input)
        } while (input.isNotEmpty())

        return list.toList()
    }

    private fun searchList(list: Set<Cell>): Set<Cell> {
        return list.flatMap { findZeroMineCells(it) }.toSet()
    }

    fun getOpenCellList(position: Position): List<Cell> {
        val cell = findCell(position) ?: return emptyList()
        return when (cell.isZeroMine()) {
            true -> getZeroMineCellList(position).flatMap { it.findNearPosition().mapNotNull { findCell(it) } }.filterIsInstance<NoneCell>().toSet().toList()
            else -> listOf(cell)
        }
    }

    private fun isAllOpen(): Boolean = cells.filterIsInstance<NoneCell>().count { !it.isOpen } == 0

    fun clickedItem(position: Position): GameStatus {
        val cell = findCell(position) ?: return GameStatus.OVER
        if (cell is MineCell) return GameStatus.OVER
        getOpenCellList(position).forEach { open(it) }
        if (isAllOpen()) return GameStatus.WIN
        return GameStatus.CONTINUE
    }
}
