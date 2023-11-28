package minesweeper

@JvmInline
value class Width(
    val value: Int
) {
    constructor(input: String) : this(input.toInt())

    operator fun minus(other: Int) = value - other
}
