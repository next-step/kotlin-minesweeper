package minesweeper.domain

class Cells(private val cells: List<Cell>, val width: Int) : List<Cell> by cells {
    val height: Int = size / width

    init {
        require(width > 0 && height > 0)
        require(cells.filter { it.bomb }.count() in 1 until size)
    }

    fun operation(): Operation {
        return Operation.Smart(cells, Matrix(width, height))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cells

        if (cells != other.cells) return false
        if (width != other.width) return false
        if (height != other.height) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cells.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        return result
    }

    fun allOpen() {
        for (cell in cells) {
            cell.open()
        }
    }

    interface Operation {
        var result: Result
        fun open(position: Position)

        class Smart(private val cells: List<Cell>, private val matrix: Matrix) : Operation {
            override lateinit var result: Result
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
                if (completed()) {
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

            private fun completed() = cells.filterNot { it.open || it.bomb }.isEmpty()
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
}
