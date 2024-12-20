package domain

@JvmInline
value class Col(val value: Int) {
    operator fun plus(other: Int): Col = Col(value + other)

    operator fun compareTo(other: Col): Int = value.compareTo(other.value)
}
