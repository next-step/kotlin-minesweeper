package domain

sealed class Cell {
    abstract val id: CellId
    abstract var isOpen: Boolean

    abstract fun addNumberHint(
        row: Int,
        col: Int,
        allCells: Cells,
    ): Cell

    open fun isMine(): Boolean = false

    open fun open(
        row: Int,
        col: Int,
        allCells: Cells,
    ): List<Position> = emptyList()

    data object Empty : Cell() {
        override val id = CellId.EMPTY
        override var isOpen: Boolean = false

        override fun addNumberHint(
            row: Int,
            col: Int,
            allCells: Cells,
        ): Cell {
            val adjacentMineCount = countAdjacentMines(row, col, allCells)
            return if (adjacentMineCount > 0) NumberCell(adjacentMineCount) else this
        }

        private fun countAdjacentMines(
            row: Int,
            col: Int,
            allCells: Cells,
        ): Int {
            return DIRECTIONS.count { (dr, dc) ->
                val newRow = row + dr
                val newCol = col + dc

                if (newRow in 0 until allCells.size && newCol in 0 until allCells[newRow].size) {
                    allCells[newRow][newCol].isMine()
                } else {
                    false
                }
            }
        }

        override fun open(
            row: Int,
            col: Int,
            allCells: Cells,
        ): List<Position> {
            if (isOpen) return emptyList()
            isOpen = true
            return DIRECTIONS.flatMap { (dr, dc) ->
                val newRow = row + dr
                val newCol = col + dc

                if (newRow in 0 until allCells.size && newCol in 0 until allCells[newRow].size) {
                    val adjacentCell = allCells[newRow][newCol]
                    if (!adjacentCell.isOpen && adjacentCell is Empty) {
                        listOf(Position(newRow, newCol)) + adjacentCell.open(newRow, newCol, allCells)
                    } else {
                        emptyList()
                    }
                } else {
                    emptyList()
                }
            }
        }
    }

    data object MineCell : Cell() {
        override val id = CellId.MINE
        override var isOpen: Boolean = false

        override fun isMine(): Boolean = true

        override fun addNumberHint(
            row: Int,
            col: Int,
            allCells: Cells,
        ): Cell {
            return this
        }
    }

    data class NumberCell(val count: Int) : Cell() {
        override val id = CellId.NUMBER
        override var isOpen: Boolean = false

        override fun addNumberHint(
            row: Int,
            col: Int,
            allCells: Cells,
        ): Cell {
            return this
        }

        override fun open(
            row: Int,
            col: Int,
            allCells: Cells,
        ): List<Position> {
            isOpen = true
            return emptyList()
        }
    }

    companion object {
        private val DIRECTIONS =
            listOf(
                -1 to -1,
                -1 to 0,
                -1 to 1,
                0 to -1,
                0 to 1,
                1 to -1,
                1 to 0,
                1 to 1,
            )

        fun create(isMine: Boolean): Cell = if (isMine) MineCell else Empty
    }
}
