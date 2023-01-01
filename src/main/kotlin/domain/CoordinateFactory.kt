package domain

object CoordinateFactory {
    private const val INVALID_HEIGHT = "올바르지 않은 높이를 열기 위해 시도하고 있어요"
    private const val INVALID_WIDTH = "올바르지 않은 너비를 열기 위해 시도하고 있어요"
    private const val COORDINATE_MIN_VALUE = 0

    fun create(row: Int, col: Int, height: Height, width: Width): Coordinate {
        return Coordinate(row, col).apply { validateCoordinate(row, col, height, width) }
    }

    private fun validateCoordinate(row: Int, col: Int, height: Height, width: Width) {
        require(row in COORDINATE_MIN_VALUE until height.value) { INVALID_HEIGHT }
        require(col in COORDINATE_MIN_VALUE until width.value) { INVALID_WIDTH }
    }
}
