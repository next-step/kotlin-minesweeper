package next.step.minesweeper.domain.board

@JvmInline
value class BoardHeight(val height: Int) {

    init {
        require(height > MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}보다 커야합니다." }
    }

    companion object {
        private const val MIN_HEIGHT = 0
    }
}
