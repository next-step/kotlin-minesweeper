package minesweeper.domain

@JvmInline
value class Width(val value: Int) {

    init {
        require(value >= MINIMUM_WIDTH) { "너비는 ${MINIMUM_WIDTH}이상이여야 합니다." }
    }

    companion object {
        const val MINIMUM_WIDTH = 1
    }
}
