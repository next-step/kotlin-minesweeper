package minesweeper.domain

sealed class Cell(val point: Point) : Comparable<Cell> {
    abstract val mine: Boolean
    abstract val count: Int
    override fun compareTo(other: Cell): Int = point.compareTo(other.point)

    abstract fun increase(): Cell
}

class MineCell(point: Point, override val count: Int = 0) : Cell(point) {
    override val mine: Boolean
        get() = true

    override fun increase(): Cell = MineCell(point, count + 1)
}

class ClearCell(point: Point, override val count: Int = 0) : Cell(point) {
    override val mine: Boolean
        get() = false

    override fun increase(): ClearCell = ClearCell(point, count + 1)
}

class Cells(private val cells: MutableList<Cell> = mutableListOf()) {

    fun add(cell: Cell) {
        require(!cells.any { it.point == cell.point })
        cells.add(cell)
        cells.sort()
    }

    fun at(point: Point): Cell = cells.find { it.point == point } ?: throw RuntimeException()

    fun replace(point: Point, cell: Cell) {
        val index = cells.indexOfFirst { it.point == point }
        cells.add(index, cell)
        cells.removeAt(index + 1)
    }
}
