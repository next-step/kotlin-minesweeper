package minesweeper.domain.field

@JvmInline
value class CoordinateValue(val value: Int) {
    init {
        require(value >= 0) { "좌표를 나타내는 값은 음수가 될수 없습니다." }
    }
}
