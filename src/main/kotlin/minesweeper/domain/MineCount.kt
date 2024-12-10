package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class MineCount(val count: Int) {
    init {
        require(count > ZERO) { INVALID_MINE_COUNT_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val INVALID_MINE_COUNT_EXCEPTION_MESSAGE = "지뢰 개수는 양수여야 합니다."
    }
}
