package minesweeper.domain

import minesweeper.exception.InvalidMineCountRangeException

@JvmInline
value class MineCount(val mineCount: Int) {
    init {
        if (mineCount < DEFAULT_MINE_COUNT) {
            throw InvalidMineCountRangeException(mineCount)
        }
    }

    companion object {
        const val DEFAULT_MINE_COUNT = 1
    }
}
