package domain

data class Point(
    val y: Int,
    val x: Int,
) {
    operator fun plus(other: Point): Point =
        Point(y + other.y, x + other.x)
}
