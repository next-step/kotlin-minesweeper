package business

import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun isAround(point: Point): Boolean = isAroundHeight(point) && isAroundWeight(point) && isDifferent(point)

    private fun isAroundHeight(point: Point) = abs(x - point.x) <= DIFFERENT_VALUE

    private fun isAroundWeight(point: Point) = abs(y - point.y) <= DIFFERENT_VALUE

    private fun isDifferent(point: Point) = this != point
    fun aroundPoints(): List<Point> {
        return createRangeAroundCoordinate(x)
            .flatMap { i -> createAdjacentPoints(i) }
            .filter { it != this }
    }

    private fun createAdjacentPoints(i: Int) = createRangeAroundCoordinate(y).map { j -> Point(i, j) }

    private fun createRangeAroundCoordinate(coordinate: Int): IntRange =
        coordinate - DIFFERENT_VALUE..coordinate + DIFFERENT_VALUE

    companion object {
        fun indexToPoint(index: Int, width: Int): Point = Point(index / width, index % width)
        const val DIFFERENT_VALUE = 1
    }
}
