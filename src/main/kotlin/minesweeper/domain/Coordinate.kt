package minesweeper.domain

data class Coordinate(
    val y: Int,
    val x: Int,
) {
    init {
        require(y >= 0) { "y축 좌표는 0 이상이어야 합니다." }
        require(x >= 0) { "x축 좌표는 0 이상이어야 합니다." }
    }
}
