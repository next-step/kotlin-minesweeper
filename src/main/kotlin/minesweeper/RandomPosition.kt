package minesweeper

class RandomPosition(
    dimensions: BoardDimensions
) : PositionGenerateStrategy {

    private val rowRange = IntRange(0, dimensions.height - 1)
    private val colRange = IntRange(0, dimensions.width - 1)

    override fun generate(): Position =
        Position(rowRange.random(), colRange.random())
}
