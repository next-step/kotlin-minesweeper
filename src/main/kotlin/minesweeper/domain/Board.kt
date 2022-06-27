package minesweeper.domain

class Board(area: Area, mineCount: MineCount, cellsGenerator: CellsGenerator = DefaultCellsGenerator()) {
    init {
        val maxCellCount = area.height * area.width
        require(mineCount <= maxCellCount) { "게임판 보다 많은 지뢰는 배치할 수 없습니다." }
    }

    val cells: Cells = cellsGenerator.generate(area, mineCount)

    fun isCompleted(): Boolean = cells.isAllBlockOpened()

    fun openAllMine() = cells.openAllMine()

    fun open(coordinate: Coordinate): BoardOpenResult {
        val cell = findCell(coordinate) ?: return BoardOpenResult.NotFound
        if (cell.isOpened()) return BoardOpenResult.AlreadyOpened

        openCell(cell)

        return when (cell) {
            is Cell.Block -> BoardOpenResult.Success
            is Cell.Mine -> BoardOpenResult.Fail
        }
    }

    private fun findCell(coordinate: Coordinate) = cells.findCell(coordinate)

    private fun findAroundOpenableCells(cell: Cell): List<Cell> = cell.coordinate
        .aroundCoordinates()
        .asSequence()
        .mapNotNull(::findCell)
        .filterNot(Cell::isOpened)
        .toList()

    private fun openCell(cell: Cell) {
        cell.open()
        if (!cell.isNearMine()) findAroundOpenableCells(cell).forEach(::openCell)
    }
}
