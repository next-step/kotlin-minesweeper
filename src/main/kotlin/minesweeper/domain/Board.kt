package minesweeper.domain

@JvmInline
value class Board(val cells: List<Cell>) {

    fun remainMineCount() = cells.count { it is Cell.Mine }

    fun groupByColumn(): Map<Int, List<Cell>> = cells.groupBy(keySelector = { it.coordinate.y })

    fun open(coordinate: Coordinate): BoardOpenResult {
        return when (val cell = findCell(coordinate)) {
            is Cell.Mine -> openMineCell(cell)
            is Cell.Block -> openBlockCell(cell)
            null -> BoardOpenResult.NotFound
        }
    }

    private fun openMineCell(cell: Cell.Mine): BoardOpenResult {
        cell.open()
        return BoardOpenResult.Fail
    }

    private fun openBlockCell(cell: Cell.Block): BoardOpenResult {
        if (cell.isOpened()) return BoardOpenResult.AlreadyOpened

        cell.open()
        openAroundCells(cell.coordinate)
        return BoardOpenResult.Success
    }

    private fun findCell(coordinate: Coordinate) = cells.find { it.coordinate == coordinate }

    private fun openAroundCells(coordinate: Coordinate) = coordinate.aroundCoordinates().forEach(::open)
}
