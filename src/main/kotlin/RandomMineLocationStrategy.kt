class RandomMineLocationStrategy : MineLocationStrategy {
    override fun generateMineLocations(boardSize: BoardSize, mineCount: Int): LandMindLocations {
        require(boardSize.area >= mineCount) {
            "지뢰판의 크기보다 지뢰의 개수가 더 많습니다. [지뢰 개수: $mineCount]"
        }
        return LandMindLocations(List(mineCount) { randomPoint(boardSize) })
    }

    private fun randomPoint(boardSize: BoardSize): Point {
        val randomX = (0 until boardSize.width).random()
        val randomY = (1 until boardSize.height).random()
        return Point(randomX, randomY)
    }
}