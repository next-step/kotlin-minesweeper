package minesweeper.domain

class MineBoard(rowCount: RowCount, columnCount: ColumnCount, mineCount: MineCount) {

    val rowSize = rowCount.count
    val columnSize = columnCount.count
    val size: Int = rowCount.count * columnCount.count

    val coordinates: List<Coordinate> = make(mineCount)

    private fun make(mineCount: MineCount): List<Coordinate> {
        val mineCoordinates: List<Int> = (0..size).shuffled().take(mineCount.count)

        return List(size) { index ->
            if (mineCoordinates.contains(index)) {
                Coordinate(CoordinateType.MINE)
            } else {
                Coordinate(CoordinateType.NONE)
            }
        }
    }

    init {
        require(mineCount.count <= size) { "지뢰 개수는 보드 크기보다 작거나 같아야 합니다." }
    }
}
