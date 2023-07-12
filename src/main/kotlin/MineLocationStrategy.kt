interface MineLocationStrategy {
    fun generateMineLocations(boardSize: BoardSize, mineCount: Int): LandMindLocations
}