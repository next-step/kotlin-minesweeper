package minesweeper.model

@JvmInline
value class Width(val value: Int) {

    init {
        require(value > 0)
    }
}
