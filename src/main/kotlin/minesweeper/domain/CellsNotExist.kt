package minesweeper.domain

object CellsNotExist : CellsOpener {

    override fun isNearCellExist(cell: Cell){
        cell.currentStatus = if (cell.cellState.getNearMineCount() == ZERO) CellsExist else CellsNotExist
    }

    override fun openNearCell(cells: List<Cell>, cell: Cell): List<Cell> {
        return emptyList()
    }

    override fun getNearCells(cells: Map<Position, Cell>, cell: Cell): List<Cell> {
        return emptyList()
    }


    private const val ZERO = 0
}
