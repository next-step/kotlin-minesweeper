package minesweeper
typealias CellKey = Int

class Cells(val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { isMineCell(it) }

    fun open(position: Position): OpenState {
        val cell = at(position)
        if (!cell.availableOpen() || cell.isMine()) return OpenState.LOSE

        cell.open()
        val visited = mutableSetOf<Position>()
        openCellAndNeighbors(cell, position, visited)
        return if (allNonMineCellsOpened()) OpenState.ALL_DONE else OpenState.CONTINUE
    }

    private fun openCellAndNeighbors(
        cell: Cell,
        position: Position,
        visited: MutableSet<Position>,
    ) {
        if (visited.contains(position) || isMineCell(cell)) return

        visited.add(position)
        val neighborMineCount = neighborsMineCount(position)
        if (neighborMineCount > 0) {
            return
        }

        cell.open()
        position.neighbors()
            .mapNotNull { values[it.key()] }
            .forEach { openCellAndNeighbors(it, it.position, visited) }
    }

    fun detectMines() {
        values.values.forEach { cell ->
            val neighborMineCount = neighborsMineCount(cell.position)
            cell.determineCell(neighborMineCount)
        }
    }

    private fun allNonMineCellsOpened(): Boolean = values.values.all { it.isOpen || isMineCell(it) }

    fun neighborsMineCount(position: Position): Int =
        Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { isMineCell(it) }

    fun at(position: Position): Cell = values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")

    private fun isMineCell(cell: Cell): Boolean = cell is Cell.MineCell

    companion object {
        fun detectCreateOf(cells: List<Cell>): Cells = Cells(cells.associateBy { it.position.key() }).apply { detectMines() }
    }
}

enum class OpenState {
    LOSE,
    CONTINUE,
    ALL_DONE,
}
