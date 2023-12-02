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

    fun forEachCell(onEachCell: (Position, CellStatus) -> Unit) {
        cells.forEach { cell ->
            onEachCell(cell.position, if (cell.isMine()) CellStatus.MINE else CellStatus.EMPTY)
        }
    }

    private fun calculateAdjacentMines(position: Position): Int {
        return mineManager.mineCounter.countMinesAround(this, position, height, width)
    }

    fun placeMineAt(position: Position) {
        findCell(position).placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).isMine()

    fun countMines(): Int = cells.count { it.isMine() }

    private fun findCell(position: Position): Cell = cells.first { it.position == position }
}
