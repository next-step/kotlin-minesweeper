package minesweeper.domain

class RandomMineFactory(private val dimension: Dimension, private val mineCount: PositiveNumber) : MineFactory {

    override fun mines(): List<Coordinate> {
        return dimension.allCoordinate().shuffled().subList(0, mineCount.number)
    }
}
