package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class Width(val value: Int) {
    init {
        require(value > ZERO) { WIDTH_INIT_VALUE_EXCEPTION }
    }

    companion object {
        private const val WIDTH_INIT_VALUE_EXCEPTION = "너비는 양수 값이어야 합니다."
    }
}
