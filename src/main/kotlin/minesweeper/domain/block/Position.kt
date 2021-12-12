package minesweeper.domain.block

import minesweeper.exception.InvalidPositionRangeException

data class Position(val x: Int = MINIMUM_X, val y: Int = MINIMUM_Y) {
    init {
        if (x < MINIMUM_X || y < MINIMUM_Y) {
            throw InvalidPositionRangeException(x, y)
        }
    }

    fun isStartHorizontal(): Boolean = y == START

    companion object {
        const val MINIMUM_X = 0
        const val MINIMUM_Y = 0
        private const val START = 1
    }
}
