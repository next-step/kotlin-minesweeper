package minesweeper.domain

data class Cell(
    val position: Position
) {
    lateinit var cellState: CellState

    var currentStatus: CellsOpener = CellsNotExist

    fun openNearCells(cells: Map<Position, Cell>) {
        currentStatus.isNearCellExist(this)
        val nearCells = currentStatus.getNearCells(cells, this)
        currentStatus.openNearCell(nearCells, this).forEach {
            it.openNearCells(cells)
        }
    }

    fun isNonMine(): Boolean {
        return cellState.isNonMine()
    }

    fun isOpen(): Boolean {
        return cellState.isOpen
    }

    fun click() {
        require(isNotClicked()) { "이미 클릭된 좌표 입니다." }
        this.cellState.click()
    }

    fun isNotClicked(): Boolean {
        return !cellState.isOpen
    }

    fun isNearMineNotExist(): Boolean {
        return this.cellState.getNearMineCount() == ZERO
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
