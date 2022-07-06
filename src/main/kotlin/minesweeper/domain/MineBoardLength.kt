package minesweeper.domain

@JvmInline
value class MineBoardLength(
    val value: Int,
) {
    init {
        require(value > 0) { "지뢰판 높이, 너비 길이는 0보다 커야합니다." }
    }
}
