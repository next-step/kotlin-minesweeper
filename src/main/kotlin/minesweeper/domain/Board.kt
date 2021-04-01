package minesweeper.domain

class Board(val cells: List<Cell>, private val matrix: Matrix) {
    val cellCount: Int = cells.size
    val height: Int = matrix.height
    val width: Int = matrix.width
    lateinit var result: Result
        private set

    init {
        require(cells.filter { it.bomb }.count() in 1 until cellCount)
    }

    constructor(cells: List<Cell>, width: Int) : this(cells, Matrix(width, cells.size / width))

    fun open(inputPosition: Position) {
        val position = inputPosition.toZeroBased()
        if (!matrix.contains(position)) {
            result = Result.OUT_OF_MATRIX
            return
        }

        val cell = cellOf(position)
        if (cell.open) {
            result = Result.OPENED
            return
        }

        cell.open()

        result = result(cell)
    }

    fun allOpen() {
        for (cell in cells) {
            cell.quietlyOpen()
        }
    }

    fun cellOf(fromIndex: Int, toIndex: Int) = cells.subList(fromIndex, toIndex)

    private fun completed() = cells.all { it.done }

    private fun cellOf(position: Position) = cells[matrix.toIndex(position)]

    private fun result(cell: Cell): Result {
        if (cell.exploded) {
            return Result.EXPLOSION
        }

        if (completed()) {
            return Result.END
        }
        return Result.SUCCESS
    }
}
