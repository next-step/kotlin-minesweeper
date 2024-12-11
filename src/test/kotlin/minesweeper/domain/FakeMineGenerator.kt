package minesweeper.domain

class FakeMineGenerator(
    private val points: List<Point>,
) : MineGenerator {
    override fun generate(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ): List<Mine> = points.map { Mine(it) }
}
