package minesweeper.domain

data class PositiveNumber(val number: Int) {
    init {
        require(number in MIN_NUMBER..MAX_NUMBER) { "1부터 30까지 입력가능합니다" }
    }

    companion object {
        private const val BOARD_MAX_SIZE = 30

        fun of(number: String): PositiveNumber {
            return PositiveNumber(number.toIntOrNull() ?: throw IllegalArgumentException("숫자가 유효하지 않습니다"))
        }

        private const val MIN_NUMBER = 1
        private const val MAX_NUMBER = BOARD_MAX_SIZE
    }
}
