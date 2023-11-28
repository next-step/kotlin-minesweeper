package minesweeper

class MineGenerator(
    private val mineCount: MineCount,
    private val randomPosition: PositionGenerateStrategy
) {

    fun generate(): Mines {
        val positions = mutableSetOf<Position>().apply {
            while (mineCount > this.size) {
                this.add(randomPosition.generate())
            }
        }
        return Mines(positions)
    }
}
