package domain

@JvmInline
value class MineCnt(
    val value: Int
) {
    init {
        require(value > THRESHOLD) { NOT_POSITIVE_MINE_CNT }
    }

    companion object {
        private const val THRESHOLD = 0
        private const val NOT_POSITIVE_MINE_CNT = "마인의 개수는 양수를 입력해야합니다."
    }
}
