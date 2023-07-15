package minesweeper.domain

/**
 * ### (x, y) 좌표계의 특정 위치를 표현합니다
 */
data class Position(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x must be greater than and equal to zero" }
        require(y >= 0) { "y must be greater than and equal to zero" }
    }
}
