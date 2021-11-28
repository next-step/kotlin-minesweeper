package minesweeper.model

@JvmInline
value class Row(val value: Int) {

    fun increment(): Row = Row(value + 1)

    fun decrement(): Row = Row((value - 1).coerceAtLeast(1))

    init {
        require(value > 0)
    }
}
