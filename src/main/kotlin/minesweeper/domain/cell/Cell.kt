package minesweeper.domain.cell

import minesweeper.domain.point.Point

sealed class Cell(val point: Point) : Comparable<Cell> {
    abstract val count: Int
    var isOpened: Boolean = false
        private set

    override fun compareTo(other: Cell): Int = point.compareTo(other.point)

    abstract fun increase(): Cell

    fun open() {
        isOpened = true
    }
}
