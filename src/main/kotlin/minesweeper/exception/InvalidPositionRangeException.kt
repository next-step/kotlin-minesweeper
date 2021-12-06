package minesweeper.exception

class InvalidPositionRangeException(x: Int, y: Int) : RuntimeException() {

    override val message: String = MESSAGE.format(x, y)

    companion object {
        private const val MESSAGE = "좌표 {x='%s', y='%s'}는 Position 의 범위가 아닙니다."
    }
}
