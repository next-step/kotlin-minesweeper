package minesweeper.model

class Cells(
    cells: List<Cell> = listOf(),
) : MinesStrategy {
    var cellList: List<Cell> = cells
        private set

    fun add(cell: Cell): Cells {
        return Cells(cellList + cell)
    }

    fun addAll(cells: List<Cell>): Cells {
        return Cells(this.cellList + cells)
    }

    override fun addMines(
        cells: Cells,
        mineCount: Int,
    ): Cells {
        repeat(mineCount) {
            cells.cellList[it].addMine()
        }

        return cells
    }

    fun addMineAroundCounts() {
        cellList.filter { it.isMine() }.forEach { mineCell ->
            Direction.entries.forEach { direction ->
                val adjacentRow = mineCell.row + direction.dx
                val adjacentColumn = mineCell.column + direction.dy
                cellList.find { it.isAround(adjacentRow, adjacentColumn) }?.addMineAroundCount()
            }
        }
    }
}
