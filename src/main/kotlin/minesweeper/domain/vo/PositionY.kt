package minesweeper.domain.vo

@JvmInline
value class PositionY(val value: Int) {
    init {
        require(value >= 0) { "위치는 음수일 수 없습니다." }
    }

    fun top(): PositionY? {
        val y = value - 1
        if (y < 0) return null
        return PositionY(value - 1)
    }

    fun bottom() = PositionY(value + 1)
}
