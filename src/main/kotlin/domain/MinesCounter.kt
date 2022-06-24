package domain

@JvmInline
value class MinesCounter(val mineCount: Int) {
    init {
        require(mineCount > 0) { MINE_INVALID_MESSAGE }
    }

    companion object {
        private const val MINE_INVALID_MESSAGE = "지뢰의 개수는 0개 이상이어야 합니다."
    }
}
