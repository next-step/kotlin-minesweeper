package minesweeper.domain

class Cells(private val cells: List<Cell>, val width: Int) : List<Cell> by cells {
    private val matrix: Matrix = Matrix(width, size / width)
    val height: Int = matrix.height

    init {
        require(cells.filter { it.bomb }.count() in 1 until size)
    }

    fun operation(): Operation {
        return Operation.Smart(this, matrix)
    }

    fun allOpen() {
        for (cell in this) {
            cell.quietlyOpen()
        }
    }

    fun completed() = filterNot { it.open || it.bomb }.isEmpty()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cells

        if (cells != other.cells) return false
        if (matrix != other.matrix) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cells.hashCode()
        result = 31 * result + matrix.hashCode()
        return result
    }
}
