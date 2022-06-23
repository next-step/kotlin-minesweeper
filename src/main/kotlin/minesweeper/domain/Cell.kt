package minesweeper.domain

data class Cell(
    val position: Position
) {
    lateinit var cellState: CellState

    fun openNearCells(cells: Map<Position, Cell>) {
        this.click()
        if (isNearMineNotExist()) openNearCell(cells)
    }

    fun isNonMine(): Boolean {
        return cellState.isNonMine()
    }

    fun isOpen(): Boolean {
        return cellState.isOpen
    }

    private fun openNearCell(cells: Map<Position, Cell>) {
        getNearCells(cells)
            .onEach { it.click() }
            .filter { it.isNearMineNotExist() }
            .forEach { it.openNearCell(cells) }
    }

    private fun getNearCells(cells: Map<Position, Cell>): List<Cell> {
        return this.position.nearCellPositions.map { cells[it]!! }.filter { it.isNonMine() && it.isNotClicked() }
    }

    private fun click() {
        require(isNotClicked()) { "이미 클릭된 좌표 입니다." }
        this.cellState.click()
    }

    private fun isNotClicked(): Boolean {
        return !cellState.isOpen
    }

    private fun isNearMineNotExist(): Boolean {
        return this.cellState.getNearMineCount() == ZERO
    }

    private fun nearCellContain(cell: Cell): Boolean {
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
