package domain

enum class AroundMineCount(
    val count: Int,
) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    companion object {
        private val MIN_AROUND_MINE_COUNT = ZERO.count
        private val MAX_AROUND_MINE_COUNT = EIGHT.count

        fun of(count: Int): AroundMineCount {
            return values().find { it.count == count }
                ?: throw IllegalStateException("주변 지뢰 개수는 $MIN_AROUND_MINE_COUNT ~ $MAX_AROUND_MINE_COUNT 이어야 합니다")
        }
    }
}
