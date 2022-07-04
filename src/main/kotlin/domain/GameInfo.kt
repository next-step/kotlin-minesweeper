package domain

data class GameInfo(
    val vertical: PositiveNumber,
    val horizontal: PositiveNumber,
    val mineCount: PositiveNumber
) {
    init {
        require(mineCount <= vertical * horizontal) { MINE_COUNT_MUST_SMALLER_THEN_BOARD_AREA }
    }

    companion object {
        const val MINE_COUNT_MUST_SMALLER_THEN_BOARD_AREA = "지뢰 개수는 보드 넓이 보다 작아야 합니다."

        fun of(vertical: Int, horizontal: Int, mineCount: Int) =
            GameInfo(
                PositiveNumber(vertical),
                PositiveNumber(horizontal),
                PositiveNumber(mineCount)
            )
    }
}
