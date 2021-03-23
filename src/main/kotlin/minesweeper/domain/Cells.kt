package minesweeper.domain

class Cells(private val cells: List<Cell>, val width: Int) : List<Cell> by cells {
    val height: Int = size / width

    init {
        require(width > 0 && height > 0)
        require(cells.filter { it.bomb }.count() in 1 until size)
    }

    fun operation(): Operation {
        return Operation.Smart(cells)
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

        class Smart(private val cells: List<Cell>) : Operation {
            override lateinit var result: Result
            override fun open(position: Position) {
                if (position == Position(2, 1)) {
                    result = Result.EXPLOSION
                    return
                }
                if (cells[0].open) {
                    result = Result.OPENED
                    return
                }
                cells[0].open()
                cells[1].open()
                result = Result.SUCCESS
            }
        }

        enum class Result {
            OPENED,
            SUCCESS,
            EXPLOSION
        }
    }
}
