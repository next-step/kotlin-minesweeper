package minesweeper.domain.board

import minesweeper.domain.common.PositiveInt
import minesweeper.domain.common.Rectangle

data class Board(
    override val width: PositiveInt,
    override val height: PositiveInt
) : Rectangle {

    companion object {
        fun of(width: Int, height: Int): Board {
            return Board(PositiveInt(width), PositiveInt(height))
        }
    }
}
