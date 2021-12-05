package minesweeper.exception

class InvalidHeightRangeException(element: Int) : RuntimeException() {

    override val message: String = MESSAGE.format(element)

    companion object {
        private const val MESSAGE = "'%s'는 올바른 Height 의 범위가 아닙니다."
    }
}
