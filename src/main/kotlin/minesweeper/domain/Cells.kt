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
                if (cell.bomb) {
                    result = Result.EXPLOSION
                    return
                }
                open(cell, matrix.around(zeroBased))
            }

            private tailrec fun open(cell: Cell, linked: List<Position>) {
                if (cell.open) {
                    result = Result.OPENED
                    return
                }

                cell.open()
                result = Result.SUCCESS

                if (cell.count != 0 || linked.isEmpty()) {
                    return
                }

                open(cellOf(linked.first()), linked.drop(1))
            }

            private fun cellOf(position: Position) = cells[matrix.toIndex(position)]
        }

        enum class Result {
            OPENED,
            SUCCESS,
            EXPLOSION
        }
    }
}
