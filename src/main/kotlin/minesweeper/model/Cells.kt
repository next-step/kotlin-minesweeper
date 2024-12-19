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
        cellList.filter { it.isMine() }.forEach { mineCell ->
            Direction.entries.forEach { direction ->
                minesCellAroundCount(mineCell, direction)
            }
        }
    }

    private fun minesCellAroundCount(mineCell: Cell, direction: Direction) {
        val adjacentRow = mineCell.row + direction.dx
        val adjacentColumn = mineCell.column + direction.dy
        cellList.find { it.isAround(adjacentRow, adjacentColumn) }?.addMineAroundCount()
    }

    fun getRowCells(row: Int): Cells {
        return Cells(cellList.filter { it.row == row})
    }
}
