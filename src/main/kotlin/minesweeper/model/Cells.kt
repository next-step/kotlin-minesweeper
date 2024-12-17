package minesweeper.model


class Cells(
    cells: List<Cell> = listOf()
) {
    var cellList: List<Cell> = cells
        private set

    fun add(cell: Cell): Cells {
        return Cells(cellList + cell)
    }

    fun addAll(cells: List<Cell>): Cells {
        return Cells(this.cellList + cells)
    }

    fun addMines(mineCount: Int) {
        val shuffledCells = cellList.shuffled()
        shuffledCells.subList(0, mineCount).forEach { it.addMine() }
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
