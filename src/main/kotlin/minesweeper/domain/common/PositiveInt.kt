package minesweeper.domain.common

@JvmInline
value class PositiveInt(val value: Int) {
    init {
        require(value >= 0) { "property must be zero or positive." }
    }

    operator fun times(other: PositiveInt): PositiveInt = PositiveInt(this.value * other.value)
    operator fun compareTo(other: Int): Int = this.value - other
    operator fun contains(range: IntRange): Boolean = range.contains(value)

    override fun toString(): String = value.toString()
}

operator fun Int.plus(other: PositiveInt): Int = this + other.value
operator fun Int.times(other: PositiveInt): Int = this * other.value
operator fun Int.div(other: PositiveInt): Int = this / other.value
operator fun Int.rem(other: PositiveInt) = this % other.value
operator fun Int.rangeTo(other: PositiveInt) = rangeTo(other.value)
operator fun Int.compareTo(other: PositiveInt) = this - other.value
operator fun IntRange.contains(other: PositiveInt) = contains(other.value)

infix fun Int.until(other: PositiveInt) = until(other.value)
