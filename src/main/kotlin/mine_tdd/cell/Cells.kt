package mine_tdd.cell

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
}
