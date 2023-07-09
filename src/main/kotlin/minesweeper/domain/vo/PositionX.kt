package minesweeper.domain.vo

@JvmInline
value class PositionX(val value: Int) {
    init {
        require(value >= 0) { "위치는 음수일 수 없습니다." }
    }

    fun left(): PositionX? {
        val x = value - 1
        if (x < 0) return null
        return PositionX(value - 1)
    }

    fun right(): PositionX {
        return PositionX(value + 1)
    }
}
