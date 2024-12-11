package minesweeper.domain

class DefaultMineGenerator : MineGenerator {
    override fun generate(
        height: Height,
        width: Width,
        mineCount: MineCount,
    ): List<Mine> {
        val generatedMines =
            List(height.value * width.value) { index ->
                val point = Point(row = index / width.value, col = index % width.value)
                Mine(point)
            }
        return generatedMines.shuffled().take(mineCount.count)
    }
}
