package domain

data class BoardInfo(
    val row: Row,
    val column: Column,
    val mineCount: MineCount
) {
    fun getCellSize() = row * column
    fun getRow() = row.value
    private fun getColumn() = column.value
    fun getMineCount() = mineCount.value

    fun isContainRange(x: Int, y: Int): Boolean {
        val rowRange = 1..getRow()
        val columnRange = 1..getColumn()

        return x in rowRange && y in columnRange
    }
}
