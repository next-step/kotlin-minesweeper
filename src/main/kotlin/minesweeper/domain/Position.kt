package minesweeper.domain

// TODO data class 유효성 검증
data class Position(
    val y: Int,
    val x: Int
) {
    init {
        require(y > 0) { "y는 0이거나 음수일 수 없습니다" }
        require(x > 0) { "x는 0이거나 음수일 수 없습니다" }
    }
}
