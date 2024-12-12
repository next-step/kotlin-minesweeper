package minesweeper.entity

class RandomMineGenerator : MineGenerator {
    override fun generate(
        allCoordinates: List<Coordinate>,
        mineCount: MineCount,
    ): Set<Coordinate> {
        mineCount.validate(allCoordinates.size)
        return allCoordinates.shuffled().take(mineCount.value).toSet()
    }
}
