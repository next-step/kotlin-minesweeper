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

class Cells : Iterable<Cell> {
    private val map: MutableMap<Point, Cell> = mutableMapOf()

    fun add(cell: Cell) {
        map.put(cell.point, cell)
    }

    fun at(point: Point): Cell = map.getOrDefault(point, null) ?: throw RuntimeException()

    fun createMine(point: Point) {
        val cell = MineCell(point)
        add(cell)
        point.adjacent()
            .mapNotNull { map.getOrDefault(it, null) }
            .filterIsInstance<ClearCell>()
            .forEach { add(it.increase()) }
    }

    override fun iterator(): Iterator<Cell> = map.values.sorted().iterator()
}
