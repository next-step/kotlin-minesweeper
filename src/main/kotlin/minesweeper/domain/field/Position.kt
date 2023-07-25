package minesweeper.domain.field

data class Position(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "x는 0 이상이어야 합니다." }
        require(y >= 0) { "y는 0 이상이어야 합니다." }
    }
}
