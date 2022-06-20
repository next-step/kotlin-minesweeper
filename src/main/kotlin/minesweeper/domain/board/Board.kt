package minesweeper.domain.board

import minesweeper.domain.common.PositiveInt

data class Board(
    val width: PositiveInt,
    val height: PositiveInt
) {
    val size: Int get() = width * height

    companion object {
        fun of(width: Int, height: Int): Board {
            return Board(PositiveInt(width), PositiveInt(height))
        }
    }
}
