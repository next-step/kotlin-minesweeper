package minesweeper.domain

import minesweeper.exception.InvalidWidthRangeException

@JvmInline
value class Width(val width: Int) {
    init {
        if (width < DEFAULT_HEIGHT) {
            throw InvalidWidthRangeException(width)
        }
    }

    companion object {
        const val DEFAULT_HEIGHT = 1
    }
}
