package minesweeper.model.point

@JvmInline
value class Vertical(
    private val value: Int,
) {
    init {
        require(value >= 0) { "음수는 허용되지 않습니다" }
    }
}
