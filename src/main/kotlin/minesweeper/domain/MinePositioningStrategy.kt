package minesweeper.domain

interface MinePositioningStrategy {
    fun getMinePositions(): List<Position>
}
