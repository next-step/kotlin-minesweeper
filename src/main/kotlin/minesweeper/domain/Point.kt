package minesweeper.domain

class Point(
    val r : Int,
    val c : Int,
) {
    init {
        require(r >= ZERO && c >= ZERO) { POINT_NEGATIVE_VALUE_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val ZERO = 0
        private const val POINT_NEGATIVE_VALUE_EXCEPTION_MESSAGE = "좌표는 음수가 될 수 없습니다."
    }
}
