package minesweeper.model

class Cells(
    cells: List<Cell> = listOf(),
) {
    var cellList: List<Cell> = cells
        private set

    fun add(cell: Cell): Cells {
        return Cells(cellList + cell)
    }

    fun addAll(cells: List<Cell>): Cells {
        return Cells(this.cellList + cells)
    }

    fun addMines(
        minesStrategy: MinesStrategy,
        mineCount: Int,
    ): Cells {
        return minesStrategy.addMines(this, mineCount)
    }

    fun addMinesAroundCounts() {
        cellList.filter { it.isMine }.forEach { mineCell ->
            minesCellAroundCount(mineCell)
        }
    }

    fun openAroundCells(row: Int, column: Int) {
        val cell = getCell(row, column)
        cell.open()
        if (cell.mineAroundCount == 0) {
            processAroundCells(cell) {
                if (!it.isOpen) {
                    openAroundCells(it.row, it.column)
                }
            }
        }
    }

    fun getRowCells(row: Int): Cells {
        return Cells(cellList.filter { it.row == row })
    }

    private fun minesCellAroundCount(cell: Cell) {
        processAroundCells(cell) { it.addMineAroundCount() }
    }

    private inline fun processAroundCells(cell: Cell, action: (Cell) -> Unit) {
        Direction.entries.forEach { direction ->
            val adjacentRow = cell.row + direction.dx
            val adjacentColumn = cell.column + direction.dy
            cellList.find { it.isAround(adjacentRow, adjacentColumn) }?.let(action)
        }
    }

    private fun getCell(row: Int, column: Int): Cell {
        return cellList.find { it.isAround(row, column) } ?: throw IllegalArgumentException("해당 셀이 없습니다.")
    }
}
