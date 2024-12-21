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

        return when (cell) {
            is MineCell -> {
                OpenState.LOSE
            }

            is NumberCell -> {
                cell.open()
                openNeighborCells(cell.neighbors())
                if (allNonMineCellsOpened()) OpenState.ALL_DONE else OpenState.CONTINUE
            }
        }
    }

    private fun openNeighborCells(neighbors: List<Position>) {
        neighbors
            .mapNotNull { values[it.key()] }
            .forEach { openCellRecursive(it) }
    }

    private fun openCellRecursive(cell: Cell) {
        if (cell.isOpen || isMineCell(cell)) return

        if (cell.isOpenable) {
            cell.open()
            openNeighborCells(cell.neighbors())
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
        fun detectCreateOf(cells: List<Cell>): Cells = Cells(cells.associateBy { it.position.key() }).apply { detectMines() }
    }
}
