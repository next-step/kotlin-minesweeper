package minesweeper.domain.cell

data class Coordinate(
    val row: Int,
    val column: Int,
) {
    fun up(): Coordinate = Coordinate(row - MOVE_AMOUNT, column)

    fun down(): Coordinate = Coordinate(row + MOVE_AMOUNT, column)

    fun right(): Coordinate = Coordinate(row, column + MOVE_AMOUNT)

    fun left(): Coordinate = Coordinate(row, column - MOVE_AMOUNT)

    companion object {
        private const val MOVE_AMOUNT = 1
    }
}
