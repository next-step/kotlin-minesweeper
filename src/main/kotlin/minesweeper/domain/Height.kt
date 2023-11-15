package minesweeper.domain

@JvmInline
value class Height(val value: Int) {

    init {
        require(value >= MINIMUM_HEIGHT) { "높이는 ${MINIMUM_HEIGHT}이상이여야 합니다." }
    }

    companion object {
        const val MINIMUM_HEIGHT = 1
    }
}
