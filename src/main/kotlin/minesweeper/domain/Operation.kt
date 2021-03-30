package minesweeper.domain

interface Operation {
    fun result(): Result

    fun open(inputPosition: Position)

    class Smart(private val board: Board, private val matrix: Matrix) : Operation {
        private lateinit var result: Result
        override fun result(): Result = result

        override fun open(inputPosition: Position) {
            val position = inputPosition.toZeroBased()
            if (!matrix.contains(position)) {
                result = Result.OUT_OF_MATRIX
                return
            }

            val cell = board.cellOf(position)
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

            if (board.completed()) {
                return Result.END
            }
            return Result.SUCCESS
        }
    }

    enum class Result {
        OPENED,
        SUCCESS,
        OUT_OF_MATRIX,
        EXPLOSION {
            override fun end() = true
        },
        END {
            override fun end() = true
        };

        open fun end() = false
    }
}
