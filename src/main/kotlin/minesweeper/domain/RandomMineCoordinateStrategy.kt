package minesweeper.domain

object RandomMineCoordinateStrategy : MineCoordinateStrategy {
    override fun mineCoordinates(coordinates: List<Coordinate>, mineCoordinateCount: Int): List<Coordinate> {
        return coordinates.shuffled().take(mineCoordinateCount)
    }
}
