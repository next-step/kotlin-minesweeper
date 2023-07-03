package domain

data class Coordinate(
    val row: Int = ROW_START_POSITION,
    val col: Int = COL_START_POSITION,
) {
    operator fun plus(other: Coordinate) = Coordinate(row + other.row, col + other.col)

    fun up() = Coordinate(row - MOVE_UNIT, col)

    fun down() = Coordinate(row + MOVE_UNIT, col)

    fun left() = Coordinate(row, col - MOVE_UNIT)

    fun right() = Coordinate(row, col + MOVE_UNIT)

    fun isOnBoard(height: Int, width: Int): Boolean {
        return row in ROW_START_POSITION until height && col in COL_START_POSITION until width
    }

    companion object {
        const val ROW_START_POSITION = 0
        const val COL_START_POSITION = 0
        private const val MOVE_UNIT = 1
    }
}
