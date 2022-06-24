package minesweeper.domain

class Board(area: Area, mineCount: MineCount, cellsGenerator: CellsGenerator = DefaultCellsGenerator()) {
    init {
        val maxCellCount = area.height * area.width
        require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
    }

    val cells: List<Cell> = cellsGenerator.generate(area, mineCount)

    fun remainMineCount(): Int = cells.count { it is Cell.Mine }

    fun groupByColumn(): Map<Int, List<Cell>> = cells.groupBy(keySelector = { it.coordinate.y })

    fun isCompleted(): Boolean = cells.filterIsInstance<Cell.Block>().all(Cell::isOpened)

    fun open(coordinate: Coordinate): BoardOpenResult {
        val cell = findCell(coordinate) ?: return BoardOpenResult.NotFound
        if (cell.isOpened()) return BoardOpenResult.AlreadyOpened

        openCell(cell)

        return when (cell) {
            is Cell.Block -> BoardOpenResult.Success
            is Cell.Mine -> BoardOpenResult.Fail
        }
    }

    fun openAllMine() {
        cells.filterIsInstance<Cell.Mine>().forEach(Cell::open)
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
