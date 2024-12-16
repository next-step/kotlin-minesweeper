package minesweeper.model

/**
 * @author 이상준
 */
class Cells(
    cells: List<Cell> = listOf()
) {
    var cells: List<Cell> = cells
        private set

    fun add(cell: Cell): Cells {
        return Cells(cells + cell)
    }

    fun addAll(cells: List<Cell>): Cells {
        return Cells(this.cells + cells)
    }

    fun addMines(mineCount: Int) {
        val shuffledCells = cells.shuffled()
        shuffledCells.subList(0, mineCount).forEach { it.addMine() }
    }

    fun addMineAroundCounts() {
        cells.filter { it.isMine() }.forEach { mineCell ->
            Direction.entries.forEach { direction ->
                val adjacentRow = mineCell.row + direction.dx
                val adjacentColumn = mineCell.column + direction.dy
                cells.find { it.isAround(adjacentRow, adjacentColumn) }?.addMineAroundCount()
            }
        }
    }
}
