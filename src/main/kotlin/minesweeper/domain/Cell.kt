package minesweeper.domain

data class Cell(
    val position: Position
) {
    lateinit var cellState: CellState

    fun click() {
        this.cellState.click()
    }

    fun isNotClicked(): Boolean {
        return !cellState.isOpen
    }

    fun isNearMineNotExist(): Boolean {
        return this.cellState.getNearMineCount() == ZERO
    }

    fun isNonMine(): Boolean {
        return cellState.isNonMine()
    }

    fun isOpen(): Boolean {
        return cellState.isOpen
    }

    fun nearCellContain(cell: Cell): Boolean {
        return this.position.nearCellPositions.contains(cell.position)
    }

    companion object {
        fun of(position: Position, minePositions: Positions): Cell {
            return Cell(position).apply {
                this.cellState = CellState.of(position, minePositions)
            }
        }

        private const val ZERO = 0
    }
}
