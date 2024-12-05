package minesweeper.domain

import minesweeper.common.ZERO

@JvmInline
value class MineCount(val value: Int) {
    operator fun compareTo(other: Int): Int {
        return value.compareTo(other)
    }

    init {
        require(value >= ZERO) { MINE_COUNT_MIN_EXCEPTION }
    }

    companion object {
        private const val MINE_COUNT_MIN_EXCEPTION = "지뢰 개수는 음수가 될 수 없습니다."
    }
}
