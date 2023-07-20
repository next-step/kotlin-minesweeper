package step4.domain.coordinate

data class Coordinate(
    val row: Int,
    val column: Int,
) {
    fun up(): Coordinate = Coordinate(row - 1, column)

    fun down(): Coordinate = Coordinate(row + 1, column)

    fun right(): Coordinate = Coordinate(row, column + 1)

    fun left(): Coordinate = Coordinate(row, column - 1)
}
