package minesweeper.domain.board

data class Width(
    val value: Int
) {
    init {
        require(value > 0) { "너비는 0보다 커야 합니다" }
    }

    val columnRange: IntRange = 0 until value
}
