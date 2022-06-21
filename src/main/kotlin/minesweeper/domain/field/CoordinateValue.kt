package minesweeper.domain.field

@JvmInline
value class CoordinateValue(val value: Int) {

    operator fun plus(other: CoordinateValue): CoordinateValue = CoordinateValue(value + other.value)

    operator fun minus(other: CoordinateValue): CoordinateValue = CoordinateValue(value - other.value)

    companion object {
        val plus: CoordinateValue
            get() = CoordinateValue(1)

        val minus: CoordinateValue
            get() = CoordinateValue(-1)

        val stay: CoordinateValue
            get() = CoordinateValue(0)

        fun move(): CoordinateValue = CoordinateValue(1)
    }
}
