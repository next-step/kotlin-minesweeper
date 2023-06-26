package minesweeper.domain

interface MineLocationGenerator {
    fun generateLocation(height: Int, width: Int, mineCount: Int): MineLocations
}
