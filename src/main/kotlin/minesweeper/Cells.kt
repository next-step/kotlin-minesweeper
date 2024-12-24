package minesweeper

typealias CellKey = Int

class Cells(val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { isMineCell(it) }

    fun neighborsMineCount(position: Position): Int =
        Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { isMineCell(it) }

    fun detectMines() {
        values.values.forEach { cell ->
            val neighborMineCount = neighborsMineCount(cell.position)
            cell.determineCell(neighborMineCount)
        }
    }

    fun at(position: Position): Cell = values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")

    fun allNonMineCellsOpened(): Boolean = values.values.all { it.isOpen || isMineCell(it) }

    fun isMineCell(cell: Cell): Boolean = cell is MineCell

    companion object {
        fun detectCreateOf(cells: List<Cell>): Cells = Cells(cells.associateBy { it.position.key() }).apply { detectMines() }
    }
}
