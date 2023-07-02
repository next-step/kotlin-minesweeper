package next.step.minesweeper.domain.board

@JvmInline
value class BoardWidth(private val width: Int) {

    init {
        require(width > MIN_WIDTH) { "너비는 ${MIN_WIDTH}보다 커야합니다." }
    }

    fun requireInRange(x: Int) = require(inRange(x)) { "x 위치는 ${MIN_WIDTH}보다 크고, $width 보다 작아야 합니다." }

    fun inRange(x: Int) = x in range()

    private fun range(): IntRange = MIN_WIDTH until width

    fun <T> rangeMap(transform: (Int) -> T): List<T> = range().map { transform(it) }

    fun width(): Int = width

    companion object {
        const val MIN_WIDTH = 0
    }
}
