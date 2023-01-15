package minesweeper.domain

class MineBoard(
    private val rowCount: RowCount,
    private val columnCount: ColumnCount,
    private val mineCount: MineCount
) {

    private val size: Int = rowCount * columnCount

    val coordinates: Coordinates = makeCoordinates()

    fun rowSize(): Int = rowCount.count

    fun columnSize(): Int = columnCount.count

    private fun makeCoordinates(): Coordinates {
        val coordinates: Coordinates = Coordinates.of(
            List(size) { index ->
                if (index in mineCount.minePositionList) return@List Coordinate(CoordinateType.MINE)
                Coordinate(CoordinateType.NONE)
            }
        )
        calculateRectMineCount(coordinates)
        return coordinates
    }

    private fun calculateRectMineCount(coordinates: Coordinates) {
        repeat(rowSize()) { rowIndex ->
            calculateRectMineColumnCount(rowIndex, coordinates)
        }
    }

    private fun calculateRectMineColumnCount(rowIndex: Int, coordinates: Coordinates) {
        val level = rowIndex * columnSize()
        repeat(columnSize()) { columnIndex ->
            val index = level + columnIndex
            val coordinate = coordinates[index]

            val positionArray = Position.values()
            repeat(positionArray.size) {
                val position = positionArray[it]
                val aroundIndex = position.calculate(rowSize(), columnSize(), index)
                if (aroundIndex >= 0 && coordinates[aroundIndex].isMine()) coordinate.counting()
            }
        }
    }
}

operator fun RowCount.times(columnCount: ColumnCount): Int = count * columnCount.count

fun MineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
