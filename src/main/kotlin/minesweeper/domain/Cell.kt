package minesweeper.domain

sealed class Cell {
    abstract val point: Point
}

data class MineCell(override val point: Point) : Cell()
data class EmptyCell(override val point: Point, var mineCount: Int = 0) : Cell()
