package domain.field

import domain.MinePositionsSelectStrategy
import domain.block.Block
import domain.block.Blocks
import domain.block.Mine
import domain.block.NormalBlock
import domain.block.Position

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(rectangle: Rectangle, minesCount: Int): MineField {
        val positions = rectangle.getAllPositions()
        val minePositions = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val blocks = createNormalBlocks(positions, minePositions) + Mine.from(minePositions)
        return MineField(rectangle, Blocks(blocks.sortedBy { it.position }))
    }

    private fun createNormalBlocks(allPositions: List<Position>, minePositions: List<Position>): List<Block> {
        val normalPositions = allPositions - minePositions
        return normalPositions.map { it.surroundings() }
            .map { countPositionsContainsMines(it, minePositions) }
            .zip(normalPositions) { minesCount, position -> NormalBlock(position, minesCount) }
    }

    private fun countPositionsContainsMines(positions: List<Position>, minePositions: List<Position>): Int {
        return positions.count { minePositions.contains(it) }
    }
}
