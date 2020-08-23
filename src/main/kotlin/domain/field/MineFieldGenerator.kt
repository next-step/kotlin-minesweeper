package domain.field

import domain.MinePositionsSelectStrategy
import domain.block.Block
import domain.block.Blocks
import domain.block.ClosedBlock
import domain.block.Mine
import domain.block.Position

class MineFieldGenerator(
    private val minePositionsSelectStrategy: MinePositionsSelectStrategy
) {
    fun create(rectangle: Rectangle, minesCount: Int): MineField {
        val positions = rectangle.getAllPositions()
        val minePositions = minePositionsSelectStrategy.getMinePositionsFrom(positions, minesCount)
        val blocks = createClosedBlocks(positions, minePositions) + Mine.from(minePositions)
        return MineField(rectangle, Blocks(blocks.sortedBy { it.position }))
    }

    private fun createClosedBlocks(allPositions: List<Position>, minePositions: List<Position>): List<Block> {
        val normalPositions = allPositions - minePositions
        return normalPositions.map { ClosedBlock(it) }
    }
}
