package minesweeper.common.value

@JvmInline
value class CoordinateValue(
    val value: Int
) : Comparable<CoordinateValue> {
    override fun compareTo(other: CoordinateValue): Int = this.value.compareTo(other.value)
}
