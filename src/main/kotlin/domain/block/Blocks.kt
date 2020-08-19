package domain.block

data class Blocks(
    val values: List<Block>
) {
    fun open(position: Position): Blocks {
        val blockToOpen = getIn(position)
        require(!blockToOpen.isMine()) { "해당 칸은 지뢰입니다!" }
        val visitedBlocks = visitBlocks(listOf(blockToOpen))
        return Blocks(createOpenedBlocks(visitedBlocks))
    }

    private fun createOpenedBlocks(visitedBlocks: List<Block>): List<Block> {
        val closedBlocks = this.values - visitedBlocks
        val openedBlocks = visitedBlocks.map { it.open() }
        return (openedBlocks + closedBlocks).sortedBy { it.position }
    }

    private fun getIn(position: Position): Block {
        return values.find { it.isAt(position) }
            ?: throw IllegalArgumentException("${position}에 해당하는 칸을 찾을 수 없습니다.")
    }

    private tailrec fun visitBlocks(blocksToVisit: List<Block>, visitedBlocks: List<Block> = emptyList()): List<Block> {
        if (blocksToVisit.isEmpty()) return visitedBlocks
        val newVisitedBlocks = visitedBlocks + blocksToVisit
        val nextVisitBlocks = getNextVisitBlocks(blocksToVisit) - newVisitedBlocks
        return visitBlocks(nextVisitBlocks, newVisitedBlocks)
    }

    private fun getNextVisitBlocks(blocks: List<Block>): List<Block> {
        return blocks.flatMap { getNextVisitBlocks(it) }.distinct()
    }

    private fun getNextVisitBlocks(block: Block): List<Block> {
        require(!block.isMine()) { "탐색하는 블럭이 지뢰일 수 없습니다. $block" }
        if (block.getMinesCount() == 0) return getSurroundingsOf(block)
        return emptyList()
    }

    private fun getSurroundingsOf(block: Block): List<Block> {
        val surroundPositions = block.getSurroundingPositions()
        return values.filter { surroundPositions.contains(it.position) }
    }
}
