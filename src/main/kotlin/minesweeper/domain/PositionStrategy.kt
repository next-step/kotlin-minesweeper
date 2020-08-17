package minesweeper.domain

interface PositionStrategy {
    fun setXPosition(width: Width): Int
    fun setYPosition(height: Height): Int
}
