package minesweeper

interface MineLocationGenerator {

    fun generateMineLocation(maximumOfX: Int, maximumOfY: Int): MineLocation
}