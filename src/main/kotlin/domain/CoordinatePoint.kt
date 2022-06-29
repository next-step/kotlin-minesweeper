package domain

@JvmInline
value class CoordinatePoint(private val int: Int) {
    init {
        require(int >= 0) { COORDINATE_POINT_POSITIVE_ERROR }
    }

    companion object {
        private const val COORDINATE_POINT_POSITIVE_ERROR = "좌표는 0이상이어야 합니다."
    }
}
