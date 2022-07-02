package domain

import domain.vo.Point

data class Coordinate(
    val x: Point,
    val y: Point,
) {

    val surroundings: List<Coordinate> by lazy {
        listOfNotNull(
            // top
            makeCoordinateOrNull(x = x - 1, y = y - 1),
            makeCoordinateOrNull(x = x, y = y - 1),
            makeCoordinateOrNull(x = x + 1, y = y - 1),
            // middle
            makeCoordinateOrNull(x = x - 1, y = y),
            makeCoordinateOrNull(x = x + 1, y = y),
            // bottom
            makeCoordinateOrNull(x = x - 1, y = y + 1),
            makeCoordinateOrNull(x = x, y = y + 1),
            makeCoordinateOrNull(x = x + 1, y = y + 1),
        )
    }

    init {
        require(x.isPositive) { "x 는 1보다 커야합니다" }
        require(y.isPositive) { "y 는 1보다 커야합니다" }
    }

    private fun makeCoordinateOrNull(x: Point, y: Point): Coordinate? =
        runCatching { Coordinate(x, y) }.getOrNull()
}
