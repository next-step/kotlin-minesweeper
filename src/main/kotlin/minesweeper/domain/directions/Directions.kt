package minesweeper.domain.directions

import minesweeper.domain.block.Position

sealed interface Directions {
    fun nextPosition(position: Position): Position
}
