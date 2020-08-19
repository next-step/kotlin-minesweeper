package domain.field

import domain.block.Block
import domain.block.Blocks
import domain.block.Position

data class MineField(
    private val rectangle: Rectangle,
    val blocks: Blocks
) {
    fun getWidth() = rectangle.width

    fun getHeight() = rectangle.height

    fun getBlocks() = blocks.values

    fun pick(position: Position): MineField {
        require(!blocks.isMineIn(position)) { "게임 끝!" }
        val pickedBlock = blocks.getIn(position)
        val visitedBlocks = visitBlocks(listOf(pickedBlock))
        return copy(blocks = blocks.openedOf(visitedBlocks))
    }

    private fun visitBlocks(blocksToVisit: List<Block>, visitedBlocks: List<Block> = emptyList()): List<Block> {
        if (blocksToVisit.isEmpty()) return visitedBlocks
        return visitBlocks(getNextVisitBlocks(blocksToVisit) - visitedBlocks, visitedBlocks + blocksToVisit)
    }

    private fun getNextVisitBlocks(blocks: List<Block>): List<Block> {
        return blocks.flatMap { getNextVisitBlocks(it) }.distinct()
    }

    private fun getNextVisitBlocks(block: Block): List<Block> {
        require(!block.isMine()) { "탐색하는 블럭이 지뢰일 수 없습니다. $block" }
        if (block.getMinesCount() == 0) return blocks.getSurroundingsOf(block)
        return emptyList()
    }
}
