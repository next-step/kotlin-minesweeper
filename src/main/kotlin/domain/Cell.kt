package domain

sealed class Cell {
    abstract val id: CellId

    fun addNumberHint(row: Int, col: Int, allCells: Cells): Cell {
        if (this is Empty) {
            val adjacentMineCount = countAdjacentMines(row, col, allCells)
            return if (adjacentMineCount > 0) NumberCell(adjacentMineCount) else this
        }
        return this
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

    data object Empty : Cell() {
        override val id = CellId.EMPTY
    }

    data object MineCell : Cell() {
        override val id = CellId.MINE
    }

    data class NumberCell(val count: Int) : Cell() {
        override val id = CellId.NUMBER
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