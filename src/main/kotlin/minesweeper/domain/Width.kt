package minesweeper.domain

private const val MIN_WIDTH_VALUE = 0

@JvmInline
value class Width(
    val value: Int,
) {
    init {
        require(value > MIN_WIDTH_VALUE) { "너비는 0보다 큰 정수여야합니다." }
    }
}
