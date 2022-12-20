package minesweeper.domain

class MineBoard(
    private val rowCount: RowCount,
    private val columnCount: ColumnCount,
    private val mineCount: MineCount
) {

    private val size: Int = calculateBoardSize()

    val coordinates: Coordinates = make()

    init {
        require(mineCount.count <= size) { "지뢰 개수는 보드 크기보다 작거나 같아야 합니다." }
    }

    fun rowSize(): Int = rowCount.count

    fun columnSize(): Int = columnCount.count

    private fun calculateBoardSize(): Int = rowCount * columnCount

    private fun make(): Coordinates {
        val mineCoordinates: List<Int> = (0..size).shuffled().take(mineCount.count)
        return Coordinates.Of(
            List(size) { index ->
                if (mineCoordinates.contains(index)) return@List Coordinate(CoordinateType.MINE)
                Coordinate(CoordinateType.NONE)
            }
        )
    }

    private operator fun RowCount.times(columnCount: ColumnCount): Int = count * columnCount.count
}
