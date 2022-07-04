package domain

@JvmInline
value class CoordinatePoint(private val int: Int) {
    init {
        require(int >= 0) { COORDINATE_POINT_POSITIVE_ERROR }
    }

    operator fun plus(x: Int): CoordinatePoint {
        return CoordinatePoint(int + x)
    }

    operator fun compareTo(i: Int) = when {
        int < i -> -1
        int > i -> 1
        else -> 0
    }

    companion object {
        private const val COORDINATE_POINT_POSITIVE_ERROR = "좌표는 0이상이어야 합니다."
    }
}
