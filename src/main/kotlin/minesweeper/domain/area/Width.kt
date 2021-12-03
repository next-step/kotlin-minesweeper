package minesweeper.domain.area

import minesweeper.exception.InvalidWidthRangeException

@JvmInline
value class Width(val width: Int = DEFAULT_WIDTH) {
    init {
        if (width < DEFAULT_WIDTH) {
            throw InvalidWidthRangeException(width)
        }
    }

    companion object {
        const val DEFAULT_WIDTH = 1
    }
}
