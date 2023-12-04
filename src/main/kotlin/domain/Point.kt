package domain

data class Point(
    val y: Int,
    val x: Int
) {

    operator fun plus(other: Point): Point =
        Point(y + other.y, x + other.x)

    fun getArea(): Int = y * x

    companion object {
        fun fromUserInput(list: List<Int>): Point {
            require(list.size == 2)
            return Point(list[1] - 1, list[0] - 1)
        }
    }
}
