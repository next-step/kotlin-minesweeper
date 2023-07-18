package step4.domain

data class Coordinate(
    val row: Int,
    val column: Int,
) {
    fun up(): Coordinate = Coordinate(row - 1, column)

    fun down(): Coordinate = Coordinate(row + 1, column)
}
