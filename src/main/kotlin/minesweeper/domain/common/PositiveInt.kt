package minesweeper.domain.common

@JvmInline
value class PositiveInt(val value: Int) {
    init {
        require(value >= 0) { "property must be zero or positive." }
    }

    operator fun times(other: PositiveInt): Int = this.value * other.value
    operator fun compareTo(other: Int): Int = this.value - other
}

operator fun Int.div(other: PositiveInt): Int = this / other.value
operator fun Int.rem(other: PositiveInt) = this % other.value
