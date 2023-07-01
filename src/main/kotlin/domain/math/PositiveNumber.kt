package domain.math

@JvmInline
value class PositiveNumber(val value: Int) {

    init {
        require(value > 0) {
            "value should be positive. your input: $value"
        }
    }

    operator fun times(other: PositiveNumber): PositiveNumber {
        return PositiveNumber(value * other.value)
    }

    operator fun compareTo(other: PositiveNumber): Int {
        return value.compareTo(other.value)
    }
}

fun Int.toPositive(): PositiveNumber {
    return PositiveNumber(this)
}
