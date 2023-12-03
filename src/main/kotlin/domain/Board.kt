package domain

import enum.CellStatus

class Board(
    private val height: Int,
    private val width: Int,
    private val mineManager: MineManager
) {
    private val cells: List<Cell> = List(height * width) { index ->
        Cell(Position(index % width, index / width))
    }

    fun placeMines(minePositions: List<Position>) {
        minePositions.forEach { placeMineAt(it) }
        cells.forEach { cell ->
            cell.adjacentMines = calculateAdjacentMines(cell.position)
        }
    }

    fun processEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        cells.forEach { cell ->
            onEachCell(cell.position, cell.status)
        }
    }

    fun countMines(): Int = cells.count { it.isMine() }

    private fun calculateAdjacentMines(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(this, position, height, width)
    }

    private fun placeMineAt(position: Position) {
        findCell(position)?.placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position)?.isMine() ?: false

    private fun findCell(position: Position): Cell? = cells.firstOrNull { it.position == position }

    fun openCell(position: Position) {
        val cell = findCell(position) ?: return
        if (!cell.isMine()) {
            openCellRecursive(cell)
        }
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
            if (adjacentCell != null && adjacentCell.status == CellStatus.EMPTY) {
                openCellRecursive(adjacentCell)
            }
        }
    }

    private fun determineAdjacentPositions(center: Position): List<Position> {
        val neighborPositions = NeighborPositions(center, height, width)
        return neighborPositions.positions
    }
}
