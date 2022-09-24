package v2minesweeper.domain

@JvmInline
value class Height(
    val value: Int,
) {
    init {
        require(value > MIN_HEIGHT_VALUE) { "높이는 0보다 큰 정수만 가능합니다. value = $value" }
    }

    companion object {
        private const val MIN_HEIGHT_VALUE = 0
    }
}
