package domain

data class Position(val row: PositiveNumber, val column: PositiveNumber) {
    constructor (row: Int, column: Int) : this(row.toPositiveNumber(), column.toPositiveNumber())

    fun getValidPositionInRectangleArea(height: PositiveNumber, width: PositiveNumber): Positions {
        val rectanglePositions = RectanglePosition.values()
        return rectanglePositions.map { position ->
            val newRow = row.value + position.row
            val newColumn = column.value + position.column
            newRow to newColumn
        }
            .filter { (row, column) ->
                row in MineSweeperMap.MAP_START_INDEX_VALUE..height.value &&
                    column in MineSweeperMap.MAP_START_INDEX_VALUE..width.value
            }
            .map { Position(it.first.toPositiveNumber(), it.second.toPositiveNumber()) }
            .toPositions()
    }
}
