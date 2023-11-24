package domain

import enum.CellStatus

class Board(private val height: Int, private val width: Int) {
    private val cells: Array<Cell> = Array(height * width) { yx ->
        Cell(Position(yx % width, yx / width))
    }

    fun placeMineAt(position: Position) {
        findCell(position).status = CellStatus.MINE
    }

    fun hasMineAt(position: Position): Boolean = findCell(position).status == CellStatus.MINE

    fun countMines(): Int = cells.count { it.status == CellStatus.MINE }

    private fun findCell(position: Position): Cell = cells[position.y * width + position.x]
}
