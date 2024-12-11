package minesweeper.domain

@JvmInline
value class FieldHeight(val height: Int) {
    init {
        require(height >= 1) { "높이는 1 이상 이어야 합니다." }
    }
}
