package minesweeper.domain

interface CellsOpener {
    fun isNearCellExist(cell: Cell)

    fun openNearCell(cells: List<Cell>, cell: Cell): List<Cell>

    fun getNearCells(cells: Map<Position, Cell>, cell: Cell): List<Cell>
}
