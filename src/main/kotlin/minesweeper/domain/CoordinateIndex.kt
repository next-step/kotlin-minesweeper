package minesweeper.domain

@JvmInline
value class CoordinateIndex(private val value: Int) {
    init {
        require(value >= 0) {
            "좌표 값은 0 이상이어야 합니다"
        }
    }
}
