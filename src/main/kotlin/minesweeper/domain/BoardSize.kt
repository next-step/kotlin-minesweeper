package minesweeper.domain

@JvmInline
value class BoardSize(val value: Int) {

    init {
        require(value = value in BOARD_SIZE_RANGE) {
            "점은 $BOARD_SIZE_RANGE 사이에 위치해야 합니다. 입력값 : $value"
        }
    }

    operator fun times(boardSize: BoardSize): BoardSize = BoardSize(value = boardSize.value * this.value)

    fun rangeZeroToSize(): IntRange = START_INDEX until value

    companion object {
        private const val START_INDEX: Int = 0
        private val BOARD_SIZE_RANGE: IntRange = 0..100
    }
}
