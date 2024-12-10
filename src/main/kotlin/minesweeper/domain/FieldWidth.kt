package minesweeper.domain

@JvmInline
value class FieldWidth(val width: Int) {
    init {
        require(width >= 1) { "너비는 1 이상 이어야 합니다." }
    }
}
