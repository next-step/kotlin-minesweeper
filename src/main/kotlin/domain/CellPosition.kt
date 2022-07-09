package domain

data class CellPosition(
    val row: Int,
    val column: Int
) {
    init {
        require(row >= FIRST_ROW_INDEX && column >= FIRST_COLUMN_INDEX) {
            "$MESSAGE_INVALID_CELL_POSITION ($row, $column)"
        }
    }

    fun apply(direction: Direction): CellPosition {
        return CellPosition(
            row = row + direction.diffRow,
            column = column + direction.diffColumn
        )
    }

    fun isInBoundaryOf(minesweeperInfo: MinesweeperInfo): Boolean {
        return row >= FIRST_ROW_INDEX &&
            row < minesweeperInfo.rowCount &&
            column >= FIRST_COLUMN_INDEX &&
            column < minesweeperInfo.columnCount
    }

    companion object {
        private const val FIRST_ROW_INDEX = 0
        private const val FIRST_COLUMN_INDEX = 0

        private const val MESSAGE_INVALID_CELL_POSITION = "0 이상의 값만 좌표로 사용할 수 있습니다. 입력된 값 : "

        fun from(index: Int, columnCount: Int): CellPosition {
            val row = index / columnCount
            val column = index % columnCount

            return CellPosition(row, column)
        }
    }
}
