package minesweeper.domain.area

import minesweeper.exception.InvalidHeightRangeException

@JvmInline
value class Height(val height: Int = DEFAULT_HEIGHT) {
    init {
        if (height < DEFAULT_HEIGHT) {
            throw InvalidHeightRangeException(height)
        }
    }

    companion object {
        const val DEFAULT_HEIGHT = 1
    }
}
