package domain

interface MineLocationStrategy {
    fun generateMineLocations(boardSize: BoardSize, mineCount: Int): LandMineLocations
}