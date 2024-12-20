package minesweeper

typealias CellKey = Int

class Cells(private val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { isMineCell(it) }

    fun detectMines() {
        values.values.forEach { cell ->
            val neighborMineCount = neighborsMineCount(cell.position)
            cell.determineCell(neighborMineCount)
        }
    }

    fun neighborsMineCount(position: Position): Int {
        return Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { it is Cell.MineCell }
    }

    fun checkMine(position: Position): Boolean {
        return isMineCell(at(position))
    }

    fun rowSize(): Int {
        return values.values
            .filter { it.x == 0 }
            .size
    }

    fun rowAt(rowIndex: Int): List<Cell> {
        return values.values
            .filter { it.matchRowIndex(rowIndex) }
    }

    private fun isMineCell(cell: Cell): Boolean {
        return cell is Cell.MineCell
    }

    private fun at(position: Position): Cell {
        return values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")
    }

    companion object {
        fun detectCreateOf(cells: List<Cell>): Cells {
            return Cells(cells.associateBy { it.position.key() }).apply {
                detectMines()
            }
        }
    }
}