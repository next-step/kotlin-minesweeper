package business

data class RowCells(private val cells: List<Cell>) {
    constructor(vararg cells: Cell) : this(cells.toList())

    val indices: IntRange
        get() = cells.indices

    operator fun get(index: Int): Cell = cells[index]

    fun isMine(index: Int): Boolean = this[index].isMine()

    fun open(index: Int): RowCells {
        val muTablesCells = cells.toMutableList()
        muTablesCells[index] = cells[index].open()
        return RowCells(muTablesCells)
    }

    fun isAllOpen(): Boolean = cells.all(Cell::isClear)
    fun processEachCellAndPoint(x: Int, action: (Cell, Point) -> Unit) =
        cells.forEachIndexed { y: Int, cell: Cell -> action(cell, Point(x, y)) }
}
