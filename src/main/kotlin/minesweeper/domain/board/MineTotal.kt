package minesweeper.domain.board

data class MineTotal(
    val value: Int
) {
    init {
        require(value > 0) { "지뢰의 수는 0보다 커야 합니다" }
    }
}
