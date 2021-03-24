package minesweeper.domain

interface Operation {
    fun result(): Result

    fun open(inputPosition: Position)

    class Smart(private val cells: Cells, private val matrix: Matrix) : Operation {
        private lateinit var result: Result
        override fun result(): Result = result

        override fun open(inputPosition: Position) {
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

            result = getResult(cell)
        }

        private fun getResult(cell: Cell): Result {
            if (cell.exploded) {
                return Result.EXPLOSION
            }

            if (cells.completed()) {
                return Result.END
            }
            return Result.SUCCESS
        }

        private fun cellOf(position: Position) = cells[matrix.toIndex(position)]
    }

    enum class Result {
        OPENED,
        SUCCESS,
        EXPLOSION,
        END,
        OUT_OF_MATRIX
    }
}
