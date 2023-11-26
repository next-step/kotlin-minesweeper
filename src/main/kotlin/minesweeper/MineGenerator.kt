package minesweeper

class MineGenerator(
    private val mineCount: Int,
    private val randomPosition: PositionGenerateStrategy
) {
    constructor(mineCount: String, randomPosition: PositionGenerateStrategy) :
        this(mineCount.toInt(), randomPosition)

    fun generate(): Mines {
        return Mines(generate(mutableSetOf()))
    }

    private tailrec fun generate(positions: MutableSet<Position>): MutableSet<Position> {
        return if (positions.size >= mineCount) positions
        else generate(
            positions.apply {
                this.add(randomPosition.generate())
            }
        )
    }
}
