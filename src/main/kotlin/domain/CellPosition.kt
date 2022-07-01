package domain

data class CellPosition(
    val row: Int,
    val column: Int
) {
    companion object {
        fun from(index: Int, columnCount: Int): CellPosition {
            val row = index / columnCount
            val column = index % columnCount

            return CellPosition(row, column)
        }
    }
}
