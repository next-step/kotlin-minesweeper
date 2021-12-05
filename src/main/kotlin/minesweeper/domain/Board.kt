package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.block.Position

data class Board(val area: Area, val blocks: List<Block>) {
    init {
        require(blocks.count { it is Mine } > MINIMUM_MINE_COUNT)
        require(area.getArea() == blocks.size)
    }

    fun scanMines(): Board {
        var blocksResult = emptyList<Block>()
        for (block in blocks) {
            var mineCount = 0
            mineCount = findMineCount(mineCount, block)
            blocksResult = addNoneBlock(block, blocksResult, mineCount)
            blocksResult = addMineBlock(block, blocksResult)
        }
        return Board(area, blocksResult)
    }

    private fun addMineBlock(
        block: Block,
        blockResult: List<Block>
    ): List<Block> {
        var blocks = blockResult
        if (block is Mine) {
            blocks = blocks + block
        }
        return blocks
    }

    fun findBlock(x: Int, y: Int): Block? {
        val targetPosition = Position(x, y)
        return blocks.find { it.getPosition() == targetPosition }
    }

    private fun findIsMine(block: Block?): Boolean {
        return block is Mine
    }

    private fun addNoneBlock(
        block: Block,
        blockResult: List<Block>,
        mineCount: Int
    ): List<Block> {
        var blocks = blockResult
        if (block is None) {
            blocks = blocks + block.updateBlock(mineCount)
        }
        return blocks
    }

    private fun findMineCount(mineCount: Int, block: Block): Int {
        var count = mineCount
        for (position in MOVABLE_POSITION) {
            count = findMines(block, position, count)
        }
        return count
    }

    private fun findMines(
        block: Block,
        position: List<Int>,
        mineCount: Int
    ): Int {
        var count = mineCount
        val targetPositionX = block.getPosition().x + position[X_POSITION]
        val targetPositionY = block.getPosition().y + position[Y_POSITION]

        if (findIsMine(findBlock(targetPositionX, targetPositionY))) {
            count++
        }
        return count
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
        private const val X_POSITION = 0
        private const val Y_POSITION = 1

        private val MOVABLE_POSITION = listOf(
            listOf(-1, 1), listOf(0, 1), listOf(1, 1), listOf(1, 0),
            listOf(1, -1), listOf(0, -1), listOf(-1, -1), listOf(-1, 0)
        )
    }
}
