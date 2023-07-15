package minesweeper.domain

interface MinePositioningStrategy {
    fun getMinePositions(): Positions
}
