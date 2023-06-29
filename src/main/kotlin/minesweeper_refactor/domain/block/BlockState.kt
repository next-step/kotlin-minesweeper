package minesweeper_refactor.domain.block

enum class BlockState {
    MINE,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    ;

    companion object {
        private val BLOCK_STATE_MAP: Map<Int, BlockState> = mapOf(
            0 to ZERO,
            1 to ONE,
            2 to TWO,
            3 to THREE,
            4 to FOUR,
            5 to FIVE,
            6 to SIX,
            7 to SEVEN,
            8 to EIGHT,
        )

        fun valueOf(aroundMineCount: Int): BlockState = requireNotNull(value = BLOCK_STATE_MAP[aroundMineCount]) {
            "주변 지뢰는 0 ~ 8 범위의 개수만 가질 수 있습니다. 입력값 : $aroundMineCount"
        }
    }
}
