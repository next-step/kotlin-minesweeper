package minesweeper.model

@JvmInline
value class Column(val value: Int) {

    fun increment(): Column = Column(value + 1)

    fun decrement(): Column = Column((value - 1).coerceAtLeast(1))

    init {
        require(value > 0)
    }
}
