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

    fun getNearMineCount(): Int {
        return this.cellState.mineCount
    }

    fun isNonMine(): Boolean {
        return cellState.isNonMine()
    }

    companion object {
        fun of(position: Position, minePositions: Positions): Cell {
            return Cell(position).apply {
                this.cellState = CellState.of(position, minePositions)
            }
        }
    }
}
