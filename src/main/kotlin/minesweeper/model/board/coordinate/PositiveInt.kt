package minesweeper.model.board.coordinate

@JvmInline
value class PositiveInt(val value: Int) {
    init {
        require(value > 0)
    }

    operator fun plus(other: PositiveInt): Int = this.value + other.value
    operator fun times(other: PositiveInt): Int = this.value * other.value
    operator fun div(other: PositiveInt): Int = this.value / other.value
    operator fun rem(other: PositiveInt): Int = this.value % other.value

    operator fun plus(other: Int): Int = this.value + other
    operator fun times(other: Int): Int = this.value * other
    operator fun div(other: Int): Int = this.value / other
    operator fun rem(other: Int): Int = this.value % other
}

operator fun Int.plus(other: PositiveInt): Int = this + other.value
operator fun Int.times(other: PositiveInt): Int = this * other.value
operator fun Int.div(other: PositiveInt): Int = this / other.value
operator fun Int.rem(other: PositiveInt): Int = this % other.value
operator fun Int.rangeTo(other: PositiveInt): IntRange = this..other.value
