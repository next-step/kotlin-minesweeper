package domain

data class CellPosition(
    val row: Int,
    val column: Int
) {
    fun apply(direction: Direction): CellPosition {
        return CellPosition(
            row = row + direction.diffRow,
            column = column + direction.diffColumn
        )
    }

    fun isInBoundaryOf(minesweeperInfo: MinesweeperInfo): Boolean {
        return row < 0 || row >= minesweeperInfo.rowCount || column < 0 || column >= minesweeperInfo.columnCount
    }

    companion object {
        fun from(index: Int, columnCount: Int): CellPosition {
            val row = index / columnCount
            val column = index % columnCount

            return CellPosition(row, column)
        }
    }
}
