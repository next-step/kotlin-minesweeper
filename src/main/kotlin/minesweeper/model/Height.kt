package minesweeper.model

@JvmInline
value class Height(val value: Int) {

    init {
        require(value > 0)
    }
}
