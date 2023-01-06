package domain

import domain.strategy.MinePositionsSelectStrategy
import domain.strategy.RandomMinePositionsSelectStrategy

class BoardFactory {
    fun generate(
        rectangle: Rectangle,
        mineCount: MineCount,
        positionsSelectStrategy: MinePositionsSelectStrategy = RandomMinePositionsSelectStrategy()
    ): Board {
        val positions = rectangle.toPositions()
        val minePositions = positionsSelectStrategy.getMinePositions(positions, mineCount)
        val blocks = createBlocks(positions, minePositions)
        return Board(blocks)
    }

    fun createBlocks(positions: List<Position>, minePositions: List<Position>): Map<Position, Block> {
        return positions.associateWith {
            val mineCount = getMineCountFromSurroundings(it, minePositions)
            if (minePositions.contains(it)) {
                Mine(it, mineCount)
            } else {
                NormalBlock(it, mineCount)
            }
        }
    }

    private fun getMineCountFromSurroundings(position: Position, minePositions: List<Position>): MineCount {
        return MineCount(position.surroundings().count { minePositions.contains(it) })
    }
}
