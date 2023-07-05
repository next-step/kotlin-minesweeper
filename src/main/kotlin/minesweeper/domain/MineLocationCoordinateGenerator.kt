package minesweeper.domain

interface MineLocationCoordinateGenerator {

    fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): MineLocation
}