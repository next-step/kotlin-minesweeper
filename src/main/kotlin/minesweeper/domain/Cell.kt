package minesweeper.domain

sealed class Cell {
    abstract fun text(): String
}

data class NumberCell(var mineCountAround: Int = 0) : Cell() {
    override fun text(): String {
        return mineCountAround.toString()
    }
}

object MineCell : Cell() {
    override fun text(): String {
        return "*"
    }
}
