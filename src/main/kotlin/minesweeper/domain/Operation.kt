package minesweeper.domain

interface Operation {
    fun result(): Result

    fun open(position: Position)

    class Smart(private val cells: Cells, private val matrix: Matrix) : Operation {
        private lateinit var result: Result
        override fun result(): Result = result

        override fun open(position: Position) {
            val zeroBased = Position(position.x - 1, position.y - 1)
            if (!matrix.contains(zeroBased)) {
                result = Result.OUT_OF_MATRIX
                return
            }
            val cell = cellOf(zeroBased)

            result = error(cell)
            if (result != Result.NONE) {
                return
            }

            result = open(zeroBased, matrix.around(zeroBased))
            if (cells.completed()) {
                result = Result.END
            }
        }

        private fun error(cell: Cell): Result {
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
            val nextCell = cellOf(next)
            if (!(nextCell.bomb || nextCell.open || nextCell.count > 0)) {
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
