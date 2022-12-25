package domain

data class BoardInfo(
    val row: Row,
    val column: Column,
    val mineCount: MineCount
) {
    fun getCellSize() = row * column
    fun getRow() = row.value
    fun getColumn() = column.value
    fun getMineCount() = mineCount.value

    fun isContainRange(x: Int, y: Int): Boolean {
        return x >= 1 && x <= getRow() && y >= 1 && y <= getColumn()
    }
}
