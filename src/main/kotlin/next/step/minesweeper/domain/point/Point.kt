package next.step.minesweeper.domain.point

data class Point(val x: Int, val y: Int) {
    init {
        require(x >= BASE_X) { "위치 x는 $BASE_X 보다 작을 수 없습니다." }
        require(y >= BASE_Y) { "위치 y는 $BASE_Y 보다 작을 수 없습니다." }
    }

    companion object {
        private const val BASE_X = 0
        private const val BASE_Y = 0
        fun base() = Point(BASE_X, BASE_Y)
    }
}
