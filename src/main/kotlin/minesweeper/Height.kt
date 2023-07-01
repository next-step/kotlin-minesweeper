package minesweeper

@JvmInline
value class Height(
    val value: Int,
) {
    init {
        require(value > MINIMUM_HEIGHT) { "높이는 ${MINIMUM_HEIGHT}보다 커야 합니다." }
    }

    companion object {
        private const val MINIMUM_HEIGHT = 0
    }
}
