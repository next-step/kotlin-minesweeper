package minesweeper.domain

object CellsExist : CellsOpener {

    override fun isNearCellExist(cell: Cell) {
    }

    override fun openNearCell(cells: List<Cell>, cell: Cell): List<Cell> {
        return cells
            .onEach { it.click() }
            .filter { it.isNearMineNotExist() }
            .also { if (it.isNotEmpty()) cell.currentStatus = CellsNotExist }
    }

    override fun getNearCells(cells: Map<Position, Cell>, cell: Cell): List<Cell> {
        return cell.position.nearCellPositions
            .map { cells[it]!! }
            .filter { it.isNonMine() && it.isNotClicked() }
            .also { if (it.isEmpty()) cell.currentStatus = CellsNotExist }
    }
}