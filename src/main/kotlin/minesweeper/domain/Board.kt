package minesweeper.domain

class Board(val cells: List<Cell>, private val matrix: Matrix) {
    val cellCount: Int = cells.size
    val height: Int = matrix.height
    val width: Int = matrix.width

    init {
        require(cells.filter { it.bomb }.count() in 1 until cellCount)
    }

    constructor(cells: List<Cell>, width: Int) : this(cells, Matrix(width, cells.size / width))

    fun operation(): Operation {
        return Operation.Smart(this, matrix)
    }

    fun allOpen() {
        for (cell in cells) {
            cell.quietlyOpen()
        }
    }

    fun completed() = cells.all { it.done }

    fun cellOf(position: Position) = cells[matrix.toIndex(position)]

    fun cellOf(fromIndex: Int, toIndex: Int) = cells.subList(fromIndex, toIndex)
}
