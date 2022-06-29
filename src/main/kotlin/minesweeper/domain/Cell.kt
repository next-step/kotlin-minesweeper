package minesweeper.domain

import minesweeper.domain.enums.CellStatus

sealed class Cell {
    abstract fun text(): String
}

data class NumberCell(var mineCountAround: Int = 0) : Cell() {
    var status = CellStatus.CLOSE

    override fun text(): String {
        return when(status) {
            CellStatus.CLOSE -> "C"
            CellStatus.OPEN -> mineCountAround.toString()
        }
    }

    fun open() {
        status = CellStatus.OPEN
    }
}

object MineCell : Cell() {
    override fun text(): String {
        return "*"
    }
}
