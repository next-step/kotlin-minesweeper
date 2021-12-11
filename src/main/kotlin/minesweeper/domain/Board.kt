package minesweeper.domain

import minesweeper.domain.area.Area
import minesweeper.domain.block.Block
import minesweeper.domain.block.Blocks
import minesweeper.domain.block.Mine
import minesweeper.domain.game.GameResult
import minesweeper.domain.game.State

data class Board(val area: Area, val blocks: Blocks) {
    init {
        require(area.getArea() == blocks.size) { AREA_BLOCK_SIZE_SHOULD_SAME }
    }

    fun scanMine(x: Int, y: Int): GameResult {
        val targetBlock = blocks.findBlock(x, y) ?: return GameResult(State.PLAY, this)
        if (targetBlock.isMine()) return GameResult(State.LOSE, this)
        val blockResult = blocks.updateBlocksByPosition(x, y)
        if (blockResult.isWinGame()) {
            return GameResult(State.WIN, Board(area, blockResult))
        }
        return GameResult(State.PLAY, Board(area, blockResult))
    }

    private fun Block.isMine(): Boolean {
        return this is Mine
    }
    companion object {
        private const val AREA_BLOCK_SIZE_SHOULD_SAME = "면적과 블록의 갯수는 같아야 합니다."
    }
}
