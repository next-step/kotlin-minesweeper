package domain

class RandomMinePositionGenerator : MinePositionGenerator {
    override fun generate(height: Int, width: Int, mineCount: Int): List<Position> {
        return (1..height).flatMap { row ->
            (1..width).map { col ->
                Position(row, col)
            }
        }
            .shuffled()
            .take(mineCount)
    }
}
