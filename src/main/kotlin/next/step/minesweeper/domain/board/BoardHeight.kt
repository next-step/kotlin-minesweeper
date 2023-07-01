package next.step.minesweeper.domain.board

@JvmInline
value class BoardHeight(private val height: Int) {
    fun range(): IntRange = MIN_HEIGHT until height

    init {
        require(height > MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}보다 커야합니다." }
    }

    companion object {
        const val MIN_HEIGHT = 0
    }
}
