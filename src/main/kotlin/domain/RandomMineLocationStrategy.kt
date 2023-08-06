package domain

class RandomMineLocationStrategy : MineLocationStrategy {
    override fun generateMineLocations(boardSize: BoardSize, mineCount: Int): MineLocations {
        val locations: MutableSet<Point> = mutableSetOf()

        while (locations.size < mineCount) {
            locations.add(randomPoint(boardSize))
        }

        return MineLocations(locations.toSet())
    }

    private fun randomPoint(boardSize: BoardSize): Point {
        val randomY = (0 until boardSize.height).random()
        val randomX = (0 until boardSize.width).random()
        return Point(randomY, randomX)
    }
}
