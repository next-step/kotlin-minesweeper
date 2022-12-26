package minesweeper.domain

@JvmInline
value class Position(private val value: Int) {
    init {
        require(value >= 0) { "0 보다 같거나 큰 수이여야 합니다." }
    }
}
