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

            result = error(position)
            if (result != Result.NONE) {
                return
            }

            result = open(position, matrix.around(position))
            if (cells.completed()) {
                result = Result.END
            }
        }

        private fun error(position: Position): Result {
            val cell = cellOf(position)
            if (cell.bomb) {
                cell.explode()
                return Result.EXPLOSION
            }
            if (cell.open) {
                return Result.OPENED
            }
            return Result.NONE
        }

        private tailrec fun open(position: Position, linked: List<Position>): Result {
            val cell = cellOf(position)
            if (!cell.bomb) {
                cell.open()
            }

            if (linked.isEmpty()) {
                return Result.SUCCESS
            }

            val next = linked.first()
            return open(next, linked.drop(1) + nextAround(next).filter { it != position })
        }

        private fun nextAround(next: Position): List<Position> {
            if (cellOf(next).canOpen()) {
                return matrix.around(next)
            }
            return emptyList()
        }

        private fun cellOf(position: Position) = cells[matrix.toIndex(position)]
    }

    enum class Result {
        OPENED,
        SUCCESS,
        EXPLOSION,
        END,
        NONE,
        OUT_OF_MATRIX
    }
}
