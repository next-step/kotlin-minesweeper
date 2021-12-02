package minesweeper.domain.block

import minesweeper.exception.InvalidPositionRangeException

data class Position(private val x: Int = DEFAULT_X, private val y: Int = DEFAULT_Y) {
    init {
        if (x < DEFAULT_X || y < DEFAULT_Y) {
            throw InvalidPositionRangeException(x, y)
        }
    }

    companion object {
        private const val DEFAULT_X = 0
        private const val DEFAULT_Y = 0
    }
}
