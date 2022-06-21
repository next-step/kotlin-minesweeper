package minesweeper.domain.field

@JvmInline
value class CoordinateValue(val value: Int) {

    operator fun plus(other: CoordinateValue): CoordinateValue = CoordinateValue(value + other.value)

    operator fun minus(other: CoordinateValue): CoordinateValue = CoordinateValue(value - other.value)

    companion object {
        fun move(): CoordinateValue = CoordinateValue(1)
    }
}
