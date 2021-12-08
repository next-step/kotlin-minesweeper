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

    fun isWinGame(): Boolean {
        return mineCount == remainCount
    }

    fun updateBlocksByPosition(
        x: Int,
        y: Int,
    ): Blocks {
        var blockResult = blocks
        MOVABLE_POSITION.forEach {
            blockResult = updateBlocks(blockResult, it.plusXposition(x), it.plusYposition(y))
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
        newX: Int,
        newY: Int,
    ): List<Block> {
        val nearBlock = findBlock(newX, newY)
        if (nearBlock != null && nearBlock is None) {
            return blockResult.replace(nearBlock.updateBlock(findNearMineCount(newX, newY))) { it == nearBlock }
        }
        return blockResult
    }

    private fun Block?.isMine(): Boolean {
        return this is Mine
    }

    private fun countsMines(
        block: Block,
        position: Position,
        mineCount: Int,
    ): Int {
        var count = mineCount
        val targetPositionX = position.plusXposition(block.getPosition())
        val targetPositionY = position.plusYposition(block.getPosition())

        if (findBlock(targetPositionX, targetPositionY).isMine()) {
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
