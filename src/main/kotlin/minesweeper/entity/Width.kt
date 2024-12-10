package minesweeper.entity

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > 0) { "너비는 0보다 커야합니다." }
    }
}
