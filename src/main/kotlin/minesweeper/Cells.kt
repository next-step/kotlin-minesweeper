package minesweeper

typealias CellKey = Int

enum class OpenState {
    LOSE,
    CONTINUE,
    ALL_DONE,
}

class Cells(val values: Map<CellKey, Cell>) {
    val mineCount: Int
        get() = values.values.count { isMineCell(it) }

    fun neighborsMineCount(position: Position): Int =
        Direction.neighbors(position)
            .mapNotNull { values[it.key()] }
            .count { isMineCell(it) }

    fun open(position: Position): OpenState {
        val cell = at(position)
        if (cell.isOpen) return OpenState.CONTINUE
        if (cell.isMine()) return OpenState.LOSE

        cell.open()
        val visited = mutableSetOf<Cell>()
        openCellAndNeighbors(cell, visited)
        return if (allNonMineCellsOpened()) OpenState.ALL_DONE else OpenState.CONTINUE
    }

    private fun openCellAndNeighbors(
        cell: Cell,
        visited: MutableSet<Cell>,
    ) {
        if (visited.contains(cell) || isMineCell(cell)) return
        visited.add(cell)

        if (cell.neighborMineCount == 0) {
            cell.open()
            cell.neighbors()
                .mapNotNull { values[it.key()] }
                .forEach { openCellAndNeighbors(it, visited) }
        }
    }

    fun detectMines() {
        values.values.forEach { cell ->
            val neighborMineCount = neighborsMineCount(cell.position)
            cell.determineCell(neighborMineCount)
        }
    }

    fun at(position: Position): Cell = values[position.key()] ?: throw IllegalArgumentException("셀이 존재하지 않습니다.")

    private fun allNonMineCellsOpened(): Boolean = values.values.all { it.isOpen || isMineCell(it) }

    private fun isMineCell(cell: Cell): Boolean = cell is MineCell

    companion object {
        fun detectCreateOf(cells: List<Cell>): Cells =
            Cells(cells.associateBy { it.position.key() }).apply { detectMines() }
    }
}
