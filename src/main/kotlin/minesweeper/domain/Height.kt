package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > ZERO) { INVALID_HEIGHT_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val INVALID_HEIGHT_EXCEPTION_MESSAGE = "높이는 양수여야 합니다."
    }
}
