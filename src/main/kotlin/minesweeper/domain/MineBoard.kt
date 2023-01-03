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
                val position = Position(rowSize(), columnSize(), index)
                if (index in mineCount.minePositionList) return@List Coordinate(position, CoordinateType.MINE)
                Coordinate(position, CoordinateType.NONE)
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
            val position = coordinates[index].position

            if (position.topLeft >= 0 && coordinates[position.topLeft].isMine()) coordinate.counting()
            if (position.top >= 0 && coordinates[position.top].isMine()) coordinate.counting()
            if (position.topRight >= 0 && coordinates[position.topRight].isMine()) coordinate.counting()
            if (position.left >= 0 && coordinates[position.left].isMine()) coordinate.counting()
            if (position.right >= 0 && coordinates[position.right].isMine()) coordinate.counting()
            if (position.bottomLeft >= 0 && coordinates[position.bottomLeft].isMine()) coordinate.counting()
            if (position.bottom >= 0 && coordinates[position.bottom].isMine()) coordinate.counting()
            if (position.bottomRight >= 0 && coordinates[position.bottomRight].isMine()) coordinate.counting()
        }
    }
}

operator fun RowCount.times(columnCount: ColumnCount): Int = count * columnCount.count

fun MineBoard(block: MineBoardBuilder.() -> Unit): MineBoard {
    return MineBoardBuilder().apply(block).build()
}
