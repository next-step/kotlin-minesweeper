package minesweeper.domain

@JvmInline
value class MineCount(val count: Int) {

    init {
        require(count >= 0) { MINE_COUNT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MINE_COUNT_ILLEGAL_ARGUMENT_EXCEPTION_MESSAGE = "지뢰 갯수는 0이상이어야 합니다."
        private const val MINE_COUNT_NUMBER_FORMAT_EXCEPTION_MESSAGE = "지뢰 갯수는 숫자여야 합니다."

        fun from(value: String): MineCount {
            return from(value.toIntOrNull() ?: throw NumberFormatException(MINE_COUNT_NUMBER_FORMAT_EXCEPTION_MESSAGE))
        }

        fun from(value: Int): MineCount {
            return MineCount(value)
        }
    }
}
