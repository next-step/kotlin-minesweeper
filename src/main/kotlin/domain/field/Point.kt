package domain.field

data class Point(
    val y: Int,
    val x: Int,
) {
    operator fun plus(other: Point): Point =
        Point(y + other.y, x + other.x)

    companion object {
        fun List<Int>.inputListToPoint(): Point {
            require(size == 2) { "Point는 2개의 Int로 구성되어야 합니다." }
            return Point(get(1) - 1, get(0) - 1)
        }
    }
}
