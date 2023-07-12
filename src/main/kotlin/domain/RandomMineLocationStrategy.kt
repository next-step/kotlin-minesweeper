package domain

class RandomMineLocationStrategy : MineLocationStrategy {
    override fun generateMineLocations(boardSize: BoardSize, mineCount: Int): MineLocations {
        return MineLocations(List(mineCount) { randomPoint(boardSize) })
    }

    private fun randomPoint(boardSize: BoardSize): Point {
        val randomY = (1 until boardSize.height).random()
        val randomX = (0 until boardSize.width).random()
        return Point(randomY, randomX)
    }
}
