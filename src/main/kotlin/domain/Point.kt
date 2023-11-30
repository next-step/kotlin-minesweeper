package domain

data class Point(
    val y: Int,
    val x: Int
) {

    constructor(list: List<Int>) : this(list[1] - 1, list[0] - 1) {
        require(list.size == 2)
    }

    operator fun plus(other: Point): Point =
        Point(y + other.y, x + other.x)

    fun getArea(): Int = y * x
}
