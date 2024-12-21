package domain

@JvmInline
value class Row(val value: Int) {
    operator fun plus(other: Int): Row = Row(value + other)

    operator fun compareTo(other: Row): Int = value.compareTo(other.value)
}
