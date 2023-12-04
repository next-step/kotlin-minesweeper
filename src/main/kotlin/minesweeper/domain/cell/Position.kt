package minesweeper.domain.cell

data class Position(
    val row: Int,
    val column: Int,
) {
    init {
        require(row >= 0) { "행은 0이상만 가능합니다" }
        require(column >= 0) { "열은 0이상만 가능합니다" }
    }
}
