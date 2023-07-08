package minesweeper.domain

class RandomMineCoordinateGenerator(
    private val height: Int,
    private val width: Int,
) : MineCoordinateGenerator {
    override fun generate(mineCount: Int): Set<Coordinate> {
        return (Coordinate.ROW_START_POSITION until height).flatMap { row ->
            (Coordinate.COL_START_POSITION until width).map { col ->
                Coordinate(row, col)
            }
        }
            .shuffled()
            .take(mineCount)
            .toSet()
    }
}
