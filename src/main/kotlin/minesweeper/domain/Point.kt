package minesweeper.domain

open class Point(x: Int, y: Int) {
    open val symbol: String = POINT_SYMBOL

    init {
        require(x >= Width.MIN_WIDTH_VALUE) { "x 좌표 값은 width의 최소 값 이상이어야합니다." }
        require(y >= Height.MIN_HEIGHT_VALUE) { "y 좌표 값은 height의 최소 값 이상이어야합니다." }
    }

    fun symbol(): String {
        return symbol
    }

    companion object {
        private const val POINT_SYMBOL = "C"
    }
}
