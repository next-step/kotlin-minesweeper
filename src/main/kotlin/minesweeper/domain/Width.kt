package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > ZERO) { INVALID_WIDTH_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val INVALID_WIDTH_EXCEPTION_MESSAGE = "너비는 양수여야 합니다."
    }
}
