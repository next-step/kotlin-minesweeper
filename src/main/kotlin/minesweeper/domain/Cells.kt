package minesweeper.domain

class Cells(val cells: List<Cell>) : List<Cell> by cells {

    fun click(position: Position) {
        require(position in cells.map { it.position }) { "유효하지 않은 좌표 입니다." }
        cells.first { it.position == position }.apply {
            require(this.isNotClicked()) { "이미 클릭된 좌표 입니다." }
            this.click()
            openNearCell(this)
        }
    }

    fun state(): BoardState {
        return when {
            isBomb() -> BoardState.BOMB
            avoidAllMine() -> BoardState.WIN
            else -> BoardState.CONTINUE
        }
    }

    private fun isBomb(): Boolean {
        return cells.any { it.cellState.isBomb() }
    }

    private fun avoidAllMine(): Boolean {
        return cells.count { it.cellState.isOpen && it.cellState.cellType == CellType.NON_MINE } == cells.count { it.cellState.cellType == CellType.NON_MINE }
    }

    private fun openNearCell(cell: Cell) {
        if (cell.getNearMineCount() == ZERO) {
            openNearCells(cell)
        }
    }

    private fun openNearCells(cell: Cell) {
        getNearCells(cell).forEach {
            openCellAndNearCells(it)
        }
    }

    private fun openCellAndNearCells(cell: Cell) {
        if (cell.isNonMine() && !cell.cellState.isOpen) {
            cell.click()
            openNearCell(cell)
        }
    }

    private fun getNearCells(cell: Cell): List<Cell> {
        return cells.filter { it.position.nearCellPositions.contains(cell.position) }
    }

    fun groupByPositionX(): List<List<Cell>> {
        return cells.groupBy { it.position.y }.map { it.value }
    }

    companion object {
        fun of(positions: Positions, minePositions: Positions): Cells {
            return positions
                .onEach { it.setNearPositions(positions) }
                .map { Cell.of(it, minePositions) }
                .let { Cells(it) }
        }

        private const val ZERO = 0
    }
}
