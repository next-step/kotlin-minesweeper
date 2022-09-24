package v2minesweeper.domain

@JvmInline
value class Width(
    val value: Int
) {
    init {
        require(value > MIN_WIDTH_VALUE) { "너비는 0보다 큰 정수만 가능합니다. value = $value" }
    }

    companion object {
        private const val MIN_WIDTH_VALUE = 0
    }
}
