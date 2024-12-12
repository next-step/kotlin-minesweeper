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
        return coordinate.adjacentCoordinates()
            .filter { it.isWithinBounds(width, height) }
            .count { _cells.findCell(it) is Cell.Mine }
    }

    fun open(coordinate: Coordinate) {
        val cell = findCell(coordinate)
        if (cell.isRevealed) return

        cell.open()
        if (shouldOpenAdjacentCells(cell, coordinate)) {
            openAdjacentEmptyCells(coordinate)
        }
    }

    private fun shouldOpenAdjacentCells(
        cell: Cell,
        coordinate: Coordinate,
    ): Boolean {
        return cell is Cell.Empty && countAroundMines(coordinate) == 0
    }

    fun determineAction(coordinate: Coordinate): Action {
        val cell = findCell(coordinate)

        return when {
            cell is Cell.Mine -> Action.GAME_OVER
            isAllSafeCellsRevealed() -> Action.GAME_CLEARED
            else -> Action.CONTINUE
        }
    }

    private fun isAllSafeCellsRevealed(): Boolean {
        return cells
            .filterIsInstance<Cell.Empty>()
            .all { it.isRevealed }
    }

    private fun openAdjacentEmptyCells(coordinate: Coordinate) {
        coordinate.adjacentCoordinates()
            .filter { it.isWithinBounds(width, height) && !findCell(it).isRevealed }
            .forEach {
                val cell = findCell(it)
                cell.open()
                if (cell is Cell.Empty && countAroundMines(cell.coordinate) == 0) {
                    openAdjacentEmptyCells(cell.coordinate)
                }
            }
    }
}
