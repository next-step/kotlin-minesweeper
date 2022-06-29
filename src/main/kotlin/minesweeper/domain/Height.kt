package minesweeper.domain

private const val MIN_HEIGHT_VALUE = 0

@JvmInline
value class Height(
    val value: Int,
) {
    init {
        require(value > MIN_HEIGHT_VALUE) { "높이는 0보다 큰 정수여야합니다." }
    }
}
