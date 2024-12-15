package minesweeper.entity

class MineField(
    val height: Height,
    val width: Width,
    private val _cells: Cells,
) {
    init {
        require(cells.size == height.value * width.value) {
            "Cells의 개수가 보드 크기와 일치하지 않습니다."
        }
    }

    val cells: List<Cell>
        get() = _cells.cells.toList()

    fun findCell(coordinate: Coordinate): Cell {
        return _cells.findCell(coordinate)
    }

    fun countAroundMines(coordinate: Coordinate): Int {
        return coordinate.findAdjacentCoordinates()
            .filter { it.isWithinBounds(width, height) }
            .count { !_cells.findCell(it).isSafe() }
    }

    fun open(coordinate: Coordinate) {
        val cell = _cells.findCell(coordinate)
        if (cell.isRevealed) return

        cell.open()
        if (shouldOpenAdjacentCells(cell)) {
            openAdjacentEmptyCells(listOf(coordinate))
        }
    }

    private fun shouldOpenAdjacentCells(cell: Cell): Boolean {
        return cell.isSafe() && countAroundMines(cell.coordinate) == NO_ADJACENT_MINES
    }

    private tailrec fun openAdjacentEmptyCells(coordinates: List<Coordinate>) {
        if (coordinates.isEmpty()) return
        val nextCoordinates =
            coordinates.flatMap { coordinate ->
                _cells.findUnrevealedNeighbors(coordinate, width, height).onEach { it.open() }
                    .filter { shouldOpenAdjacentCells(it) }
                    .map { it.coordinate }
            }
        openAdjacentEmptyCells(nextCoordinates)
    }

    fun determineAction(): Action {
        return when {
            _cells.hasRevealedMine() -> Action.GAME_OVER
            isAllSafeCellsRevealed() -> Action.GAME_CLEARED
            else -> Action.CONTINUE
        }
    }

    private fun isAllSafeCellsRevealed(): Boolean {
        return cells
            .filter(Cell::isSafe)
            .all { it.isRevealed }
    }

    companion object {
        private const val NO_ADJACENT_MINES = 0
    }
}
