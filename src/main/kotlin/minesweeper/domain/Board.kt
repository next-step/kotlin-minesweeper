package minesweeper.domain

@JvmInline
value class Board(val cells: List<Cell>) {

    fun remainMineCount() = cells.count { it is Cell.Mine }

    fun groupByColumn(): Map<Int, List<Cell>> = cells.groupBy(keySelector = { it.coordinate.y })

    fun open(coordinate: Coordinate): BoardOpenResult {
        val cell = findCell(coordinate) ?: return BoardOpenResult.NotFound
        if (cell.isOpened()) return BoardOpenResult.AlreadyOpened

        openCell(cell)

        return when (cell) {
            is Cell.Block -> BoardOpenResult.Success
            is Cell.Mine -> BoardOpenResult.Fail
        }
    }

    private fun findCell(coordinate: Coordinate) = cells.find { it.coordinate == coordinate }

    private fun findAroundOpenableCells(cell: Cell): List<Cell> = cell.coordinate
        .aroundCoordinates()
        .mapNotNull(::findCell)
        .filterNot(Cell::isOpened)

    private fun openCell(cell: Cell) {
        cell.open()
        if (!cell.isNearMine()) findAroundOpenableCells(cell).forEach(::openCell)
    }
}
