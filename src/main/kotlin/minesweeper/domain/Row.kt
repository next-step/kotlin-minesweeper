package minesweeper.domain

@JvmInline
value class Row(
    val value: Int,
) {
    init {
        require(value >= MINIMUM_ROW) { "행은 ${MINIMUM_ROW}이상 이어야 합니다." }
    }

    companion object {
        private const val MINIMUM_ROW = 0
    }
}
