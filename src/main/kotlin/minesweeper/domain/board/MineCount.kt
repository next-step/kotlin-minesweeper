package minesweeper.domain.board

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value >= ZERO_COUNT) { "지뢰 갯수는 $ZERO_COUNT 이상이어야 합니다." }
    }

    companion object {
        private const val ZERO_COUNT = 0
        val ZERO = MineCount(ZERO_COUNT)
    }
}
