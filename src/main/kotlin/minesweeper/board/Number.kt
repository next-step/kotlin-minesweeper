package minesweeper.board

@JvmInline
value class Number(
    val value: Int
) {
    constructor(input: String) : this(input.toInt())

    operator fun minus(other: Int) = value - other

    operator fun minus(other: Number) = value - other.value
    operator fun compareTo(other: Int) = this.value - other
    operator fun times(other: Number) = Number(this.value * other.value)

    fun decrease(): Number = Number(this.value - 1)
}
