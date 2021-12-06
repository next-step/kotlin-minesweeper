package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Mine
import minesweeper.domain.block.None
import minesweeper.domain.block.Position

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

    fun findBlock(x: Int, y: Int): Block? {
        val targetPosition = Position(x, y)
        return blocks.find { it.getPosition() == targetPosition }
    }

    private fun Block?.isMine(): Boolean {
        return this is Mine
    }

    fun findNearMineCount(x: Int, y: Int): Int {
        var count = 0
        val findBlock = findBlock(x, y) ?: return 0

        for (position in MOVABLE_POSITION) {
            count = countsMines(findBlock, position, count)
        }
        return count
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
            Position(-1, 1), Position(0, 1), Position(1, 1), Position(1, 0),
            Position(1, -1), Position(0, -1), Position(-1, -1), Position(-1, 0)
        )
    }
}
