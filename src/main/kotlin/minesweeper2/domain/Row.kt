package minesweeper2.domain

@JvmInline
value class Row(val value: Int) {
    init {
        require(value > 0) { "너비의 값은 0보다 커야 합니다." }
    }
}
