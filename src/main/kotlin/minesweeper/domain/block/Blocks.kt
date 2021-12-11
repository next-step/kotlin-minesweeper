package minesweeper.domain.block

data class Blocks(val blocks: List<Block> = emptyList()) {
    init {
        require(blocks.count { it is Mine } > MINIMUM_MINE_COUNT) { MINIMUM_MINE_REQUIRED }
    }

    val size: Int
        get() = blocks.size

    private val remainCount = blocks.size - hasVisitedCount

    private val mineCount: Int
        get() = blocks.count { it is Mine }

    private val hasVisitedCount: Int
        get() = blocks.count() { it.hasVisited() }

    fun findBlock(x: Int, y: Int): Block? {
        return blocks.find { it.getPosition() == Position(x, y) }
    }

    fun findBlock(position: Position): Block? {
        return blocks.find { it.getPosition() == position }
    }

    fun isWinGame(): Boolean {
        return mineCount == remainCount
    }

    fun updateBlocksByPosition(
        x: Int,
        y: Int,
    ): Blocks {
        var blockResult = blocks
        MOVABLE_POSITION.forEach {
            blockResult = updateBlocks(blockResult, it.moveTo(Position(x, y)))
        }
        return Blocks(blockResult)
    }

    fun getBlockByIndex(index: Int): Block {
        return blocks[index]
    }

    private fun findNearMineCount(x: Int, y: Int): Int {
        var count = 0
        val foundBlock = findBlock(x, y) ?: return 0

        for (position in MOVABLE_POSITION) {
            count = countsMines(foundBlock, position, count)
        }
        return count
    }

    private fun updateBlocks(
        blockResult: List<Block>,
        position: Position,
    ): List<Block> {
        val nearBlock = findBlock(position.x, position.y)
        if (nearBlock != null && nearBlock is None) {
            return blockResult.replace(nearBlock.updateBlock(findNearMineCount(position.x,
                position.y))) { it == nearBlock }
        }
        return blockResult
    }

    private fun countsMines(
        block: Block,
        position: Position,
        mineCount: Int,
    ): Int {
        var count = mineCount
        val targetPosition = position.moveTo(block.getPosition())

        if (findBlock(targetPosition)?.isMine()!!) {
            count++
        }
        return count
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
        private const val MINIMUM_MINE_REQUIRED = "최소 1개 이상 지뢰가 있어야합니다."

        private val MOVABLE_POSITION = listOf(
            Position(-1, 1), Position(0, 1), Position(1, 1), Position(1, 0), Position(0, 0),
            Position(1, -1), Position(0, -1), Position(-1, -1), Position(-1, 0)
        )
    }
}
