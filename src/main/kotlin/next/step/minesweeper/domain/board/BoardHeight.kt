package next.step.minesweeper.domain.board

@JvmInline
value class BoardHeight(private val height: Int) {

    init {
        require(height > MIN_HEIGHT) { "높이는 ${MIN_HEIGHT}보다 커야합니다." }
    }

    fun requireInRange(y: Int) = require(inRange(y)) { "y 위치는 ${MIN_HEIGHT}보다 크고, $height 보다 작아야 합니다." }

    fun inRange(y: Int) = y in range()

    private fun range(): IntRange = MIN_HEIGHT until height

    fun <T> rangeMap(transform: (Int) -> T): List<T> = range().map { transform(it) }

    fun height(): Int = height

    companion object {
        const val MIN_HEIGHT = 0
    }
}
