package minesweeper.domain.area

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > MINIMUM_WIDTH) { MINIMUM_WIDTH_REQUIRED }
    }

    companion object {
        private const val MINIMUM_WIDTH = 0
        private const val MINIMUM_WIDTH_REQUIRED = "최소 0보다 커야합니다"
    }
}
