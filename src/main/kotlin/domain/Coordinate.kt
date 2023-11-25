package domain

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= MIN_X && y >= MIN_Y) { "x 좌표는 최소 ${MIN_X}, y 좌표는 최소 $MIN_Y 이상을 만족해야합니다." }
    }

    companion object {
        private const val MIN_X = 0
        private const val MIN_Y = 0
    }
}
