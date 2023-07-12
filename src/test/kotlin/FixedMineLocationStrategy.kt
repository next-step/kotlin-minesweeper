class FixedMineLocationStrategy(
    private val mineLocations: LandMineLocations
) : MineLocationStrategy {
    override fun generateMineLocations(boardSize: BoardSize, mineCount: Int): LandMineLocations {
        require(boardSize.area >= mineCount) {
            "지뢰판의 크기보다 지뢰의 개수가 더 많습니다. [지뢰 개수: $mineCount]"
        }
        return mineLocations
    }
}