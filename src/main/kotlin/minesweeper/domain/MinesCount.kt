package minesweeper.domain

import minesweeper.exception.InvalidMinesCountRangeException

@JvmInline
value class MinesCount(val minesCount: Int) {
    init {
        if (minesCount < DEFAULT_MINES_COUNT) {
            throw InvalidMinesCountRangeException(minesCount)
        }
    }

    companion object {
        const val DEFAULT_MINES_COUNT = 1
    }
}
