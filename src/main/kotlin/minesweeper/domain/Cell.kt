package minesweeper.domain

import minesweeper.domain.enums.CellStatus

sealed class Cell {
    abstract fun text(): String
    abstract fun open(): Boolean
}

data class NumberCell(var mineCountAround: Int = 0) : Cell() {
    var status = CellStatus.CLOSE

    override fun text(): String {
        return when (status) {
            CellStatus.CLOSE -> "C"
            CellStatus.OPEN -> mineCountAround.toString()
        }
    }

    override fun open(): Boolean {
        status = CellStatus.OPEN
        return true
    }
}

object MineCell : Cell() {
    override fun text(): String {
        return "C"
    }

    override fun open(): Boolean {
        return false
    }
}
