package minesweeper.domain

sealed class Cell {
    abstract val point: Point
    var isOpen: Boolean = false
        protected set

    abstract fun open(): Result
}

data class MineCell(override val point: Point) : Cell() {
    override fun open(): Result {
        isOpen = true
        return Result.LOSE
    }
}

data class EmptyCell(override val point: Point, val mineCount: Int) : Cell() {
    override fun open(): Result {
        isOpen = true
        return Result.CONTINUE
    }
}
