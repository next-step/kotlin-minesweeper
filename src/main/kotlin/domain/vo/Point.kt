package domain.vo

@JvmInline
value class Point(private val value: Int) : Comparable<Point> {

    val isPositive: Boolean
        get() = value > 0

    override fun compareTo(other: Point): Int =
        value compareTo other.value

    operator fun plus(other: Int): Point =
        Point(value + other)

    operator fun minus(other: Int): Point =
        Point(value - other)
}
