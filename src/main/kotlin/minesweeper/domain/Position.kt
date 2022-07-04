package minesweeper.domain

data class Position(
    val x: Int,
    val y: Int,
) {
    init {
        require(x > 0 && y > 0) { "x, y 위치 조건은 0보다 큰 값만 허용합니다." }
    }
}
