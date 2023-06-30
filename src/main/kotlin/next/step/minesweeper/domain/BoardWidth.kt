package next.step.minesweeper.domain

data class BoardWidth(val width: Int) {
    init {
        require(width > MIN_WIDTH) { "너비는 ${MIN_WIDTH}보다 커야합니다." }
    }

    companion object {
        private const val MIN_WIDTH = 0
        fun of(width: Int): BoardWidth = BoardWidth(width)
    }
}