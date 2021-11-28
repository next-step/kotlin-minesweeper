package minesweeper.model

@JvmInline
value class Column(val value: Int) {

    init {
        require(value > 0)
    }
}
