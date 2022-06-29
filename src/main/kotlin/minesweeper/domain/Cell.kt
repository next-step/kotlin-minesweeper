package minesweeper.domain

import minesweeper.domain.enums.CellStatus

sealed class Cell {
    abstract fun open(): Boolean
}

data class NumberCell(var mineCountAround: Int = 0) : Cell() {
    var status = CellStatus.CLOSE

    override fun open(): Boolean {
        status = CellStatus.OPEN
        return true
    }
}

object MineCell : Cell() {
    override fun open(): Boolean {
        return false
    }
}
