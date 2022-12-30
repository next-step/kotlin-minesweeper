package domain

data class Coordinate(
    val row: Int,
    val col: Int,
) {
    constructor(row: Int, col: Int, height: Height, width: Width) : this(row, col) {
        require(row in COORDINATE_MIN_VALUE until height.value) { INVALID_HEIGHT }
        require(col in COORDINATE_MIN_VALUE until width.value) { INVALID_WIDTH }
    }

    fun getNearByCoordinates(): List<Coordinate> {
        val range = (-1..1)
        return range.flatMap { dx ->
            range.map { dy -> Coordinate(row + dx, col + dy) }
                .filter { it != this }
        }
    }

    companion object {
        private const val INVALID_HEIGHT = "올바르지 않은 높이를 열기 위해 시도하고 있어요"
        private const val INVALID_WIDTH = "올바르지 않은 너비를 열기 위해 시도하고 있어요"
        private const val COORDINATE_MIN_VALUE = 0
    }
}
