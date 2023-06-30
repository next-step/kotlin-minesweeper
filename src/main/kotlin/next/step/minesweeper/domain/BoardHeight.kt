package next.step.minesweeper.domain

@JvmInline
value class BoardHeight(val height: Int) {
    init {
        require(height > MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}보다 커야합니다." }
    }

    companion object {
        private const val MIN_HEIGHT = 0
        fun of(height: Int): BoardHeight = BoardHeight(height)
    }
}