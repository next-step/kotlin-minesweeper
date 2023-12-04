package domain

import enum.CellStatus

data class Cell(val position: Position, var status: CellStatus = CellStatus.CLOSED, var adjacentMines: Int = 0) {
    fun placeMine() {
        status = CellStatus.MINE
    }

    fun isMine(): Boolean = status == CellStatus.MINE
}
