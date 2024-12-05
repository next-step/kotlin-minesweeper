package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class Height(val value: Int) {
    init {
        require(value > ZERO) { HEIGHT_INIT_VALUE_EXCEPTION }
    }

    companion object {
        private const val HEIGHT_INIT_VALUE_EXCEPTION = "높이는 양수 값이어야 합니다."
    }
}
