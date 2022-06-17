package minesweeper.domain.vo

@JvmInline
value class NumberOfMine(
    val value: Int
) {
    init {
        require(value >= 0) { "지뢰 갯수는 음수가 될수 없습니다." }
    }
}
