package domain

import inteface.MineCounter
import inteface.MinePlacer

class Board(
    val height: Int, val width: Int,
    private val minePlacer: MinePlacer,
    private val mineCounter: MineCounter
) {
    private val cells: List<Cell> = List(height * width) { index ->
        Cell(Position(index % width, index / width))
    }

    fun initializeBoard(mineCount: Int) {
        minePlacer.placeMines(this, mineCount)
        cells.forEach { cell ->
            cell.adjacentMines = mineCounter.countMinesAround(this, cell.position)
        }
    }

    fun placeMineAt(position: Position) {
        findCell(position).placeMine()
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).isMine()

    fun countMines(): Int = cells.count { it.isMine() }

    private fun findCell(position: Position): Cell = cells.first { it.position == position }
}
