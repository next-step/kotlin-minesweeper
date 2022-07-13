package minesweeper.dto

@JvmInline
value class MineCount(
    val value: Int,
) {
    init {
        require(value >= 0) { "지뢰 개수는 음수일 수 없습니다." }
    }

    fun isZero(): Boolean = value == 0
}
