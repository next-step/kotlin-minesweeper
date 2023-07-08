package tdd.domain

data class Coordinate(
    val row: Int,
    val col: Int,
) {
    fun up() = Coordinate(row - MOVE_UNIT, col)

    fun down() = Coordinate(row + MOVE_UNIT, col)

    fun left() = Coordinate(row, col - MOVE_UNIT)

    fun right() = Coordinate(row, col + MOVE_UNIT)

    fun upLeft() = Coordinate(row - MOVE_UNIT, col - MOVE_UNIT)

    fun upRight() = Coordinate(row - MOVE_UNIT, col + MOVE_UNIT)

    fun downLeft() = Coordinate(row + MOVE_UNIT, col - MOVE_UNIT)

    fun downRight() = Coordinate(row + MOVE_UNIT, col + MOVE_UNIT)

    companion object {
        private const val MOVE_UNIT = 1
    }
}
