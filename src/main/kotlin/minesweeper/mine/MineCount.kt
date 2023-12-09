package minesweeper.mine

@JvmInline
value class MineCount(
    private val value: Int
) {
    constructor(input: String) : this(input.toInt())

    operator fun compareTo(other: Int) = this.value - other
}
