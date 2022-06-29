package domain.vo

@JvmInline
value class Point(private val value: Int) : Comparable<Point> {

    init {
        require(value > 0) { "위치는 1 이상이어야 합니다" }
    }

    override fun compareTo(other: Point): Int =
        value compareTo other.value
}
