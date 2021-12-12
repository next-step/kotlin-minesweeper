package minesweeper.domain.block

import minesweeper.exception.InvalidPositionRangeException

data class Position(val x: Int = MINIMUM_X, val y: Int = MINIMUM_Y) {
    constructor(x: String, y: String) : this(Integer.valueOf(x), Integer.valueOf(y))

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
