package minesweeper.domain.flag

enum class BlockState(val exposureName: String) {
    HIDDEN(exposureName = "C"),
    MINE(exposureName = "*"),
    ZERO(exposureName = "0"),
    ONE(exposureName = "1"),
    TWO(exposureName = "2"),
    THREE(exposureName = "3"),
    FOUR(exposureName = "4"),
    FIVE(exposureName = "5"),
    SIX(exposureName = "6"),
    SEVEN(exposureName = "7"),
    EIGHT(exposureName = "8"),
    ALREADY_OPEN(exposureName = "T"),
    ;

    companion object {
        private val BLOCK_STATE_MAP: Map<String, BlockState> = values().associateBy { it.exposureName }

        fun valueOf(aroundMineCount: Int): BlockState = requireNotNull(
            value = BLOCK_STATE_MAP[aroundMineCount.toString()]
        ) {
            "주변 지뢰는 0 ~ 8 범위의 개수만 가질 수 있습니다. 입력값 : $aroundMineCount"
        }
    }
}
