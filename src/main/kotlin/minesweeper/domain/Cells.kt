package minesweeper.domain

class Cells(private val list: List<Cell>) : List<Cell> by list {

    fun isAllBlockOpened(): Boolean = filterIsInstance<Cell.Block>().all(Cell::isOpened)

    fun openAllMine() {
        filterIsInstance<Cell.Mine>().forEach(Cell::open)
    }

    fun open(coordinate: Coordinate): CellsOpenResult {
        val cell = find(coordinate) ?: return CellsOpenResult.NotFound
        if (cell.isOpened()) return CellsOpenResult.AlreadyOpened

        openCell(cell)

        return when (cell) {
            is Cell.Block -> CellsOpenResult.Success
            is Cell.Mine -> CellsOpenResult.Fail
        }
    }

    private fun find(coordinate: Coordinate): Cell? = find { it.coordinate == coordinate }

    private fun findAroundOpenableCells(cell: Cell): List<Cell> = cell.coordinate
        .aroundCoordinates()
        .asSequence()
        .mapNotNull(this::find)
        .filterNot(Cell::isOpened)
        .toList()

    private fun openCell(cell: Cell) {
        cell.open()
        if (!cell.isNearMine()) findAroundOpenableCells(cell).forEach(::openCell)
    }
}
