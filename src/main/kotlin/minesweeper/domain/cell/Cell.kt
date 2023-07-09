package minesweeper.domain.cell

import minesweeper.domain.point.Point

sealed class Cell(val point: Point) : Comparable<Cell> {
    abstract val count: Int
    var opened: Boolean = false
        private set
    override fun compareTo(other: Cell): Int = point.compareTo(other.point)

    abstract fun increase(): Cell

    fun checkOpened() {
        opened = true
    }

    open fun close(): NotOpenedCell = NotOpenedCell(point, count)
}

class NotOpenedCell(point: Point, override val count: Int = 0, private val mine: Boolean = false) : Cell(point) {

    override fun increase(): Cell = NotOpenedCell(point, count + 1)

    fun open(): Cell = when {
        mine -> MineCell(point, count)
        count == 0 -> ClearCell(point, count)
        count > 0 -> HazardCell(point, count)
        else -> throw RuntimeException()
    }

    override fun close(): NotOpenedCell = throw RuntimeException()
}
