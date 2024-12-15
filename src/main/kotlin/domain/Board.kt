package domain

class Board(
    val height: Int,
    val width: Int,
    val mineCount: Int,
) {
    private val _cells = mutableListOf<Cell>()

    val cells: List<Cell>
        get() = _cells.toList()

    fun addCell(cell: Cell) {
        _cells.add(cell)
    }
}
