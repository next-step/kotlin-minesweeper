package minesweeper.entity

@JvmInline
value class MineCount(
    val value: Int,
) {
    init {
        require(value > 0) { "지뢰 개수는 0보다 작을 수 없습니다." }
    }
}
