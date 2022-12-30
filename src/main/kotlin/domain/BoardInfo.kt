package domain

data class BoardInfo(
    val row: Row,
    val column: Column,
    val mineCount: MineCount
) {
    fun getCellSize() = row * column
    private fun getRow() = row.value
    private fun getColumn() = column.value

    fun isContainRange(coordinate: Coordinate): Boolean {
        val rowRange = 1..getRow()
        val columnRange = 1..getColumn()

        return coordinate.x.value in rowRange && coordinate.y.value in columnRange
    }
}
