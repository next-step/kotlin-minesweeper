package minesweeper.domain

@JvmInline
value class MineCount(val count: Int) {
    init {
        require(count >= 1) { "지뢰 개수는 1 이상 이어야 합니다." }
    }
}
