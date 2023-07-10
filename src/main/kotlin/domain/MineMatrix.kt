package domain

class MineMatrix(
    private val minePositionGenerator: PositionGenerator? = null,
    private val height: Int,
    private val width: Int,
    private val mineCount: Int
) {
    private val minePositions: Set<Position> = generateMinePositions()

    fun allCells(): List<Cell> {
        return (0 until height).map { thisHeight ->
            (0 until width).map { thisWidth ->
                val position = Position(thisWidth, thisHeight)
                if (minePositions.contains(position)) Cell.mine(thisWidth, thisHeight) else Cell.ground(thisWidth, thisHeight)
            }
        }.flatten().sort()
    }

    private fun generateMinePositions() = minePositionGenerator().generate(mineCount).toSet()

    private fun minePositionGenerator() = minePositionGenerator ?: randomPositionGenerator()

    private fun randomPositionGenerator(): PositionGenerator = RandomPositionGenerator(DefaultRandomGenerator(), 0, height - 1, 0, width - 1)

    private fun List<Cell>.sort(): List<Cell> = sortedWith { leftCell, rightCell ->
        if (leftCell.position.y != rightCell.position.y) leftCell.position.y.compareTo(rightCell.position.y)
        leftCell.position.x.compareTo(rightCell.position.x)
    }
}
