package minesweeper.domain.flag

private const val NOT_BLOCK_VALUE: Int = Int.MIN_VALUE

enum class BlockState(val value: Int = NOT_BLOCK_VALUE) {
    HIDDEN,
    MINE,
    ZERO(value = 0),
    ONE(value = 1),
    TWO(value = 2),
    THREE(value = 3),
    FOUR(value = 4),
    FIVE(value = 5),
    SIX(value = 6),
    SEVEN(value = 7),
    EIGHT(value = 8),
    ALREADY_OPEN,
    ;

    companion object {
        private val BLOCK_STATE_MAP: Map<Int, BlockState> = values().filterNot { it.value == NOT_BLOCK_VALUE }
            .associateBy { it.value }

        fun valueOf(aroundMineCount: Int): BlockState = requireNotNull(
            value = BLOCK_STATE_MAP[aroundMineCount]
        ) {
            "주변 지뢰는 0 ~ 8 범위의 개수만 가질 수 있습니다. 입력값 : $aroundMineCount"
        }
    }
}
