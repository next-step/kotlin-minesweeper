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

    interface Operation {
        var result: Result
        fun open(position: Position)

        class Smart(private val cells: List<Cell>, private val matrix: Matrix) : Operation {
            override lateinit var result: Result
            override fun open(position: Position) {
                val zeroBased = Position(position.x - 1, position.y - 1)
                val cell = cellOf(zeroBased)

                result = error(cell)
                if (result != Result.NONE) {
                    return
                }

                result = open(cell, matrix.around(zeroBased))
            }

            private fun error(cell: Cell): Result {
                if (cell.bomb) {
                    return Result.EXPLOSION
                }
                if (cell.open) {
                    return Result.OPENED
                }
                return Result.NONE
            }

            private tailrec fun open(cell: Cell, linked: List<Position>): Result {
                cell.open()

                if (cells.filterNot { it.open }.isEmpty()) {
                    return Result.END
                }

                if (cell.count != 0 || linked.isEmpty()) {
                    return Result.SUCCESS
                }

                return open(cellOf(linked.first()), linked.drop(1))
            }

            private fun cellOf(position: Position) = cells[matrix.toIndex(position)]
        }

        enum class Result {
            OPENED,
            SUCCESS,
            EXPLOSION,
            END,
            NONE
        }
    }
}
