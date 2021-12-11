package minesweeper.domain.block

import minesweeper.exception.InvalidPositionRangeException

data class Position(val x: Int = DEFAULT_X, val y: Int = DEFAULT_Y) {
    init {
        if (x < DEFAULT_X || y < DEFAULT_Y) {
            throw InvalidPositionRangeException(x, y)
        }
    }

    fun isStartHorizontal(): Boolean = y == DEFAULT_Y

    companion object {
        const val DEFAULT_X = 0
        const val DEFAULT_Y = 0
    }
}
