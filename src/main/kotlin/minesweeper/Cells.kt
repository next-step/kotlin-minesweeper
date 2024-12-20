package minesweeper

typealias CellKey = Int

class Cells(private val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { isMineCell(it) }

    fun open(position: Position) {
        val cell = at(position)
        if (cell.availableOpen().not()) {
            return
        }
        if (cell.isMine()) {
            throw IllegalArgumentException("지뢰가 있는 셀은 열 수 없습니다.")
        }


        cell.open()
        val visited = mutableSetOf<Position>()
        open(neighborsCells(position), visited)
    }

    private fun cells(): List<Cell> {
        return values.values.toList()
    }

    private fun open(neighborsCells: Cells, visited: MutableSet<Position>) {
        neighborsCells.cells().forEach { cell ->
            if (visited.contains(cell.position) || cell.isOpen || isMineCell(cell)) {
                return@forEach
            }

            visited.add(cell.position)

            val neighborMineCount = neighborsMineCount(cell.position)
            if (neighborMineCount == 0) {
                cell.open()
                open(neighborsCells(cell.position), visited)
            }
        }
    }

    private fun neighborsCells(position: Position): Cells {
        return Cells(
            Direction.neighbors(position)
                .mapNotNull { values[it.key()] }
                .associateBy { it.position.key() }
        )
    }

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

    fun at(position: Position): Cell {
        return values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")
    }

    fun rowAt(rowIndex: Int): List<Cell> {
        return values.values
            .filter { it.matchRowIndex(rowIndex) }
    }

    private fun isMineCell(cell: Cell): Boolean {
        return cell is Cell.MineCell
    }

    companion object {
        fun detectCreateOf(cells: List<Cell>): Cells {
            return Cells(cells.associateBy { it.position.key() }).apply {
                detectMines()
            }
        }
    }
}
