package minesweeper.domain

class MineBoard(
    private val rowCount: RowCount,
    private val columnCount: ColumnCount,
    private val mineCount: MineCount
) {

    private val size: Int = calculateBoardSize()

    val coordinates: Coordinates = make()

    fun rowSize(): Int = rowCount.count

    fun columnSize(): Int = columnCount.count

    private fun calculateBoardSize(): Int = rowCount * columnCount

    private fun make(): Coordinates {
        return Coordinates.of(
            List(size) { index ->
                if (index in mineCount.makeMinePositionList()) return@List Coordinate(CoordinateType.MINE)
                Coordinate(CoordinateType.NONE)
            }
        )
    }
}

operator fun RowCount.times(columnCount: ColumnCount): Int = count * columnCount.count

fun MineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
