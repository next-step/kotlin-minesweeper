package domain

data class Coordinate(
    val row: Int,
    val col: Int,
) {
    fun getNearByCoordinates(): List<Coordinate> {
        val range = (-1..1)
        return range.flatMap { dx ->
            range.map { dy ->
                Coordinate(row + dx, col + dy)
            }.filter { it != this }
        }
    }
}
