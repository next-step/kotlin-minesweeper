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

    fun placeMines(mineCount: Int) {
        val minePositions = mineManager.minePlacementStrategy.placeMines(height, width, mineCount)
        minePositions.forEach { placeMineAt(it) }
    }

    fun initializeCells() {
        cells.forEach { it.status = CellStatus.CLOSED }
    }

    fun isMinePresentAt(position: Position): Boolean {
        return hasMineAt(position)
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        cells.forEach { cell ->
            onEachCell(cell.position, cell.status)
        }
    }

    fun countMinesAround(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(this, position)
    }

    fun openCell(position: Position): Boolean {
        val cell = findCell(position) ?: return false
        if (cell.isMine()) return true

        openCellRecursive(cell)
        return false
    }

    private fun openCellRecursive(cell: Cell) {
        if (shouldNotOpenCell(cell)) return

        cell.status = CellStatus.OPEN
        if (cell.adjacentMines == 0) {
            openAdjacentCells(cell)
        }
    }

    private fun shouldNotOpenCell(cell: Cell): Boolean {
        return cell.status == CellStatus.OPEN || cell.status == CellStatus.MINE
    }

    private fun openAdjacentCells(cell: Cell) {
        determineAdjacentPositions(cell.position).forEach { adjacentPosition ->
            val adjacentCell = findCell(adjacentPosition)
            if (adjacentCell != null && adjacentCell.status == CellStatus.CLOSED) {
                openCellRecursive(adjacentCell)
            }
        }
    }

    fun placeMineAt(position: Position) {
        findCell(position)?.placeMine()
    }

    fun hasMineAt(position: Position): Boolean {
        return findCell(position)?.isMine() ?: false
    }

    private fun findCell(position: Position): Cell? {
        return cells.firstOrNull { it.position == position }
    }

    private fun determineAdjacentPositions(center: Position): List<Position> {
        val neighborPositions = NeighborPositions(center, height, width)
        return neighborPositions.positions
    }
}
