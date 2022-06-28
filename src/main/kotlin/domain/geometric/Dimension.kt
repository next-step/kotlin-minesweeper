package domain.geometric

data class Dimension(
    val width: Int,
    val height: Int,
) {
    val area: Int = width * height

    init {
        require(width > 0 && height > 0) {
            "너비와 높이는 모두 0보다 큰 값이어야 합니다. 입력된 너비 = $width, 입력된 높이 = $height"
        }
    }

    fun fill(): List<Location> {
        val rows = (0 until height).toList()
        val columns = (0 until width).toList()
        return rows.flatMap { row ->
            columns.mapToLocation(row)
        }
    }

    private fun List<Int>.mapToLocation(row: Int): List<Location> {
        return this.map { col -> Location(LocationValue(row), LocationValue(col)) }
    }

    fun isFilled(locations: List<Location>): Boolean {
        val filledLocations = fill().toSet()
        val intersects = filledLocations.intersect(locations.toSet())
        return intersects.size == filledLocations.size
    }
}
