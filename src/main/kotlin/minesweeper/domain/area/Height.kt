package minesweeper.domain.area

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > MINIMUM_HEIGHT) { MINIMUM_HEIGHT_REQUIRED }
    }

    companion object {
        private const val MINIMUM_HEIGHT = 0
        private const val MINIMUM_HEIGHT_REQUIRED = "최소 0보다 커야합니다"
    }
}
