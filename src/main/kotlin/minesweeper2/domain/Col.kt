package minesweeper2.domain

@JvmInline
value class Col(val value: Int) {
    init {
        require(value > 0) { "높이의 값은 0보다 커야 합니다." }
    }
}
