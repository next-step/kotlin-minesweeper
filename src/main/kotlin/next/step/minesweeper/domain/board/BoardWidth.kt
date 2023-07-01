package next.step.minesweeper.domain.board

@JvmInline
value class BoardWidth(private val width: Int) {
    fun range(): IntRange = MIN_WIDTH until width

    init {
        require(width > MIN_WIDTH) { "너비는 ${MIN_WIDTH}보다 커야합니다." }
    }

    companion object {
        const val MIN_WIDTH = 0
    }
}
