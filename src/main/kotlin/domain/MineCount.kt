package domain

@JvmInline
value class MineCount(val value: Int) {
    init {
        require(value > 0) { INVALID_MINE_COUNT }
    }

    companion object {
        private const val INVALID_MINE_COUNT = "지뢰 개수는 전체 칸의 개수보다 작아야 합니다."
    }
}
