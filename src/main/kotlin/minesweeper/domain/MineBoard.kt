package minesweeper.domain

class MineBoard(rowCount: RowCount, columnCount: ColumnCount, mineCount: MineCount) {

    val rowSize = rowCount.count
    val columnSize = columnCount.count
    private val size: Int = rowSize * columnSize

    val coordinates: List<Coordinate> = make(mineCount)

    init {
        require(mineCount.count <= size) { "지뢰 개수는 보드 크기보다 작거나 같아야 합니다." }
    }

    private fun make(mineCount: MineCount): List<Coordinate> {
        val mineCoordinates: List<Int> = (0..size).shuffled().take(mineCount.count)

        return List(size) { index ->
            if (mineCoordinates.contains(index)) return@List Coordinate(CoordinateType.MINE)
            Coordinate(CoordinateType.NONE)
        }
    }
}
