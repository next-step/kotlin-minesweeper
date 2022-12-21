package domain

import domain.strategy.MinePositionsSelectStrategy
import domain.strategy.RandomMinePositionsSelectStrategy

class BoardFactory(
    private val positionsSelectStrategy: MinePositionsSelectStrategy = RandomMinePositionsSelectStrategy()
) {
    fun generate(rectangle: Rectangle, mineCount: MineCount): Board {
        val positions = rectangle.toPositions()
        val minePositions = positionsSelectStrategy.getMinePositions(positions, mineCount)
        val blocks = positions.associateWith { if (minePositions.contains(it)) Mine(it) else NormalBlock(it) }
        return Board(rectangle, blocks)
    }
}
