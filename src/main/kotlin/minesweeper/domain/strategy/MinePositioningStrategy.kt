package minesweeper.domain.strategy

import minesweeper.domain.Positions

interface MinePositioningStrategy {
    fun getMinePositions(): Positions
}
