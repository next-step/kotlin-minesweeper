package minesweeper.domain

@JvmInline
value class PositiveInt(val value: Int) {
    init {
        require(value > 0) { "0 이하의 숫자는 입력할 수 없습니다." }
    }

    operator fun times(other: PositiveInt): PositiveInt {
        return PositiveInt(value * other.value)
    }

    operator fun minus(other: PositiveInt): PositiveInt {
        return PositiveInt(value - other.value)
    }
}
