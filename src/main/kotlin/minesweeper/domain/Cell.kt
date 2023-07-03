package minesweeper.domain

sealed class Cell(val point: Point) : Comparable<Cell> {
    abstract val mine: Boolean
}

class MineCell(point: Point) : Cell(point) {
    override val mine: Boolean
        get() = true

    override fun compareTo(other: Cell): Int = point.compareTo(other.point)
}

class ClearCell(point: Point) : Cell(point) {
    override val mine: Boolean
        get() = false

    override fun compareTo(other: Cell): Int = point.compareTo(other.point)
}
