package minesweeper.model

@JvmInline
value class Row(val value: Int) {

    init {
        require(value > 0)
    }
}
