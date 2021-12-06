package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.block.Position
import minesweeper.domain.block.replace
import minesweeper.domain.game.GameResult
import minesweeper.domain.game.State

data class Board(val area: Area, val blocks: List<Block>) {
    init {
        require(blocks.count { it is Mine } > MINIMUM_MINE_COUNT) { MINIMUM_MINE_REQUIRED }
        require(area.getArea() == blocks.size) { AREA_BLOCK_SIZE_SHOULD_SAME }
    }

    fun scanMines(): Board {
        val blocksResult = mutableListOf<Block>()
        for (block in blocks) {
            addBlocks(block, blocksResult)
        }
        return Board(area, blocksResult)
    }

    fun scanMine(x: Int, y: Int): GameResult {
        val targetBlock = findBlock(x, y) ?: return GameResult(State.PLAY, this)
        if (targetBlock.isMine()) return GameResult(State.LOSE, this)
        var blockResult = blocks
        for (position in MOVABLE_POSITION) {
            val newX = x + position.x
            val newY = y + position.y
            blockResult = updateBlocks(blockResult, newX, newY)
        }
        val mineCount = blocks.count { it is Mine }
        val hasVisitedCount = blockResult.count() { it.hasVisited() }
        val remainCount = blocks.size - hasVisitedCount
        if (mineCount == remainCount) {
            return GameResult(State.WIN, Board(area, blockResult))
        }
        return GameResult(State.PLAY, Board(area, blockResult))
    }

    fun findBlock(x: Int, y: Int): Block? {
        val targetPosition = Position(x, y)
        return blocks.find { it.getPosition() == targetPosition }
    }

    private fun updateBlocks(
        blockResult: List<Block>,
        newX: Int,
        newY: Int
    ): List<Block> {
        var blockResult1 = blockResult
        val nearBlock = findBlock(newX, newY)
        if (nearBlock != null && nearBlock is None) {
            blockResult1 =
                blockResult1.replace(nearBlock.updateBlock(findNearMineCount(newX, newY))) { it == nearBlock }
        }
        return blockResult1
    }

    fun findNearMineCount(x: Int, y: Int): Int {
        var count = 0
        val foundBlock = findBlock(x, y) ?: return 0

        for (position in MOVABLE_POSITION) {
            count = countsMines(foundBlock, position, count)
        }
        return count
    }

    private fun addBlocks(
        block: Block,
        blocksResult: MutableList<Block>
    ) {
        if (block is None) {
            val mineCount = findNearMineCount(block)
            blocksResult.add(block.updateBlock(mineCount))
        }
        if (block.isMine()) {
            blocksResult.add(block)
        }
    }

    private fun Block?.isMine(): Boolean {
        return this is Mine
    }

    private fun findNearMineCount(block: Block): Int {
        var count = 0
        for (position in MOVABLE_POSITION) {
            count = countsMines(block, position, count)
        }
        return count
    }

    private fun countsMines(
        block: Block,
        position: Position,
        mineCount: Int
    ): Int {
        var count = mineCount
        val targetPositionX = block.getPosition().x + position.x
        val targetPositionY = block.getPosition().y + position.y

        if (findBlock(targetPositionX, targetPositionY).isMine()) {
            count++
        }
        return count
    }

    companion object {
        private const val MINIMUM_MINE_COUNT = 0
        private const val MINIMUM_MINE_REQUIRED = "최소 1개 이상 지뢰가 있어야합니다."
        private const val AREA_BLOCK_SIZE_SHOULD_SAME = "면적과 블록의 갯수는 같아야 합니다."

        private val MOVABLE_POSITION = listOf(
            Position(-1, 1), Position(0, 1), Position(1, 1), Position(1, 0), Position(0, 0),
            Position(1, -1), Position(0, -1), Position(-1, -1), Position(-1, 0)
        )
    }
}
