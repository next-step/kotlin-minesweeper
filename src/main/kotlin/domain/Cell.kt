package domain

import enum.CellStatus

class Cell(val position: Position, var status: CellStatus = CellStatus.CLOSED, private var adjacentMines: Int = 0) {
    val isMine: Boolean
        get() = status == CellStatus.MINE

    val isOpen: Boolean
        get() = status == CellStatus.OPEN

    val shouldOpen: Boolean
        get() = status == CellStatus.CLOSED

    val isAdjacentMinesZero: Boolean
        get() = adjacentMines == 0

    fun placeMine() {
        status = CellStatus.MINE
    }

    fun open() {
        status = CellStatus.OPEN
    }

    fun close() {
        status = CellStatus.CLOSED
    }

    fun setAdjacentMines(count: Int) {
        adjacentMines = count
    }
}
