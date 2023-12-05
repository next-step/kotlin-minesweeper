package domain

import enum.CellStatus

class Board(
    val height: Int,
    val width: Int,
    private val mineManager: MineManager
) {
    val cells: List<Cell> = List(height * width) { index ->
        Cell(Position(index % width, index / width))
    }

    fun placeMines(mineCount: Int, firstMove: Position) {
        val excludedPositions = NeighborPositions(firstMove, height, width).positions + firstMove
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount, excludedPositions)
        minePositions.forEach { placeMineAt(it) }
        setAdjacentMineCounts()
    }

    private fun setAdjacentMineCounts() {
        cells.forEach { cell ->
            val count = mineManager.mineCounter.countMinesAround(this, cell.position)
            cell.setAdjacentMines(count)
        }
    }

    fun closeAllCells() {
        cells.forEach(Cell::close)
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        cells.forEach { cell ->
            onEachCell(cell.position, cell.status)
        }
    }

    fun openCell(position: Position): Boolean {
        val cell = findCell(position)
            ?: throw IllegalArgumentException("해당 위치에 셀이 없습니다: $position")

        if (cell.isMine) {
            return true
        }
        openCellAndAdjacentIfRequired(cell)
        return false
    }

    private fun openCellAndAdjacentIfRequired(cell: Cell) {
        if (cell.shouldOpen) {
            cell.open()
            if (cell.isAdjacentMinesZero) {
                openAdjacentCells(cell)
            }
        }
    }

    private fun openAdjacentCells(cell: Cell) {
        determineAdjacentPositions(cell.position).forEach { adjacentPosition ->
            findCell(adjacentPosition)?.let { adjacentCell ->
                openCellAndAdjacentIfRequired(adjacentCell)
            }
        }
    }

    private fun placeMineAt(position: Position) {
        findCell(position)?.placeMine()
    }

    fun isWinConditionMet(): Boolean {
        val nonMineCells = cells.count { !it.isMine }
        val openCells = cells.count { it.isOpen }
        return nonMineCells == openCells
    }

    fun findCell(position: Position): Cell? {
        return cells.firstOrNull { it.position == position }
    }

    private fun determineAdjacentPositions(center: Position): List<Position> {
        return NeighborPositions(center, height, width).positions
    }
}
