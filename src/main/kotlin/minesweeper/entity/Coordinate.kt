package minesweeper.entity

data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x는 0보다 커야합니다." }
        require(y >= 0) { "y는 0보다 커야합니다." }
    }
}
