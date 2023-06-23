package minesweeper.domain

@JvmInline
value class BoardPoint(val value: Int) {

    init {
        require(value = value in BOARD_POINT_RANGE) {
            "점은 $BOARD_POINT_RANGE 사이에 위치해야 합니다. 입력값 : $value"
        }
    }

    operator fun times(boardPoint: BoardPoint): BoardPoint = BoardPoint(value= boardPoint.value * this.value)

    fun rangeZeroToPoint(): IntRange = START_INDEX until value

    companion object {
        private const val START_INDEX: Int = 0
        private val BOARD_POINT_RANGE: IntRange = 0..100
    }
}
