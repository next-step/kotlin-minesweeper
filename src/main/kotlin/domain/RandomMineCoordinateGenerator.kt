package domain

class RandomMineCoordinateGenerator(
    private val height: Int,
    private val width: Int,
) : MineCoordinateGenerator {
    override fun generate(mineCount: Int): Set<Coordinate> {
        return (Coordinate.ROW_START_POSITION..height).flatMap { row ->
            (Coordinate.COL_START_POSITION..width).map { col ->
                Coordinate(row, col)
            }
        }
            .shuffled()
            .take(mineCount)
            .toSet()
    }
}
