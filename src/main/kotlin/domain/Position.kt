package domain

data class Position(val row: PositiveNumber, val column: PositiveNumber) {
    constructor (row: Int, column: Int) : this(row.toPositiveNumber(), column.toPositiveNumber())

    fun getValidPositionInRectangleArea(height: PositiveNumber, width: PositiveNumber): Positions =
        (ARRAY_OF_DISTANCE_ROW.indices).map {
            val newRow = row.value + ARRAY_OF_DISTANCE_ROW[it]
            val newColumn = column.value + ARRAY_OF_DISTANCE_COLUMN[it]
            newRow to newColumn
        }
            .filter { (row, column) ->
                row in MineSweeperMap.MAP_START_INDEX_VALUE..height.value &&
                    column in MineSweeperMap.MAP_START_INDEX_VALUE..width.value
            }
            .map { Position(it.first.toPositiveNumber(), it.second.toPositiveNumber()) }
            .toPositions()

    companion object {
        val ARRAY_OF_DISTANCE_ROW = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val ARRAY_OF_DISTANCE_COLUMN = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
    }
}
