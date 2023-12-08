package domain

import enum.CellStatus
import enum.GameState

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

    fun openCell(position: Position): GameState {
        val cell = findCell(position) ?: return GameState.IN_PROGRESS
        if (cell.isMine) {
            cell.status = CellStatus.MINE
            return GameState.LOST
        }

        openCellRecursively(setOf(position))
        return if (isWinConditionMet()) GameState.WON else GameState.IN_PROGRESS
    }

    private tailrec fun openCellRecursively(positionsToOpen: Set<Position>) {
        if (positionsToOpen.isEmpty()) return

        val nextPositionsToOpen = mutableSetOf<Position>()
        for (position in positionsToOpen) {
            val cell = findCell(position) ?: continue
            if (!cell.shouldOpen) continue

            cell.status = CellStatus.OPEN
            if (cell.isAdjacentMinesZero) {
                nextPositionsToOpen.addAll(
                    determineAdjacentPositions(position).filter { adjPos ->
                        findCell(adjPos)?.shouldOpen == true
                    }
                )
            }
        }
        openCellRecursively(nextPositionsToOpen)
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

    companion object {
        fun create(height: Int, width: Int, mineManager: MineManager): Board {
            val board = Board(height, width, mineManager)
            board.closeAllCells()
            return board
        }
    }
}
