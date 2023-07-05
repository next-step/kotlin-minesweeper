package minesweeper.domain

interface CoordinateGenerator {
    fun generateCoordinate(maximumOfValue: Int): Int
}
