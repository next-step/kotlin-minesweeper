package minesweeper.domain

interface CoordinateGenerator {

    fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): MineLocation
}