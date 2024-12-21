package domain

sealed class Cell {
    abstract val id: CellId

    abstract fun addNumberHint(row: Int, col: Int, allCells: Cells): Cell

    data object Empty : Cell() {
        override val id = CellId.EMPTY

        override fun addNumberHint(row: Int, col: Int, allCells: Cells): Cell {
            val adjacentMineCount = countAdjacentMines(row, col, allCells)
            return if (adjacentMineCount > 0) NumberCell(adjacentMineCount) else this
        }

        private fun countAdjacentMines(row: Int, col: Int, allCells: Cells): Int {
            return DIRECTIONS.count { (dr, dc) ->
                val newRow = row + dr
                val newCol = col + dc
                if (newRow in 0 until allCells.size && newCol in 0 until allCells[newRow].size) {
                    allCells[newRow][newCol] is MineCell
                } else false
            }
        }
    }

    data object MineCell : Cell() {
        override val id = CellId.MINE

        override fun addNumberHint(row: Int, col: Int, allCells: Cells): Cell {
            return this // Mines do not change to another type.
        }
    }

    data class NumberCell(val count: Int) : Cell() {
        override val id = CellId.NUMBER

        override fun addNumberHint(row: Int, col: Int, allCells: Cells): Cell {
            return this // Mines do not change to another type.
        }
    }

    companion object {
        private val DIRECTIONS = listOf(
            -1 to -1, -1 to 0, -1 to 1,
            0 to -1, 0 to 1,
            1 to -1, 1 to 0, 1 to 1
        )

        fun create(isMine: Boolean): Cell = if (isMine) MineCell else Empty
    }
}
