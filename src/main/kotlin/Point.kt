import kotlin.math.abs

data class Point(val x: Int, val y: Int) {
    fun isAround(point: Point): Boolean =
        abs(x - point.x) <= DIFFERENT_VALUE && abs(y - point.y) <= DIFFERENT_VALUE && this != point

    companion object {
        const val DIFFERENT_VALUE = 1
    }
}
