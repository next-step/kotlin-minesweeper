package domain

import domain.strategy.MinePositionsSelectStrategy
import domain.strategy.RandomMinePositionsSelectStrategy

class MineFieldFactory(
    private val positionsSelectStrategy: MinePositionsSelectStrategy = RandomMinePositionsSelectStrategy()
) {
    fun generate(rectangle: Rectangle, mineCount: Int): MineField {
        val positions = Position.createAll(rectangle)

        val minePositions = positionsSelectStrategy.getMinePositions(positions, mineCount)
        val mines = minePositions.map { Mine(it) }

        val normalPositions = positions - minePositions.toSet()
        val normalBlocks = normalPositions.map { NormalBlock(it) }
        val allBlocks = normalBlocks + mines
        return MineField(rectangle, allBlocks.sortedBy { it.position })
    }
}
