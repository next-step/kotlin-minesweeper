package minesweeper.domain

class Board(private val cells: List<Cell>, private val matrix: Matrix) : List<Cell> by cells {
    val height: Int = matrix.height
    val width: Int = matrix.width

    init {
        require(cells.filter { it.bomb }.count() in 1 until size)
    }

    constructor(cells: List<Cell>, width: Int) : this(cells, Matrix(width, cells.size / width))

    fun operation(): Operation {
        return Operation.Smart(this, matrix)
    }

    fun allOpen() {
        for (cell in this) {
            cell.quietlyOpen()
        }
    }

    fun completed() = all { it.done }
}
