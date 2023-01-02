package minesweeper.ui

import minesweeper.domain.Block
import minesweeper.domain.Blocks

class ConsoleResultView : ResultView {
    override fun drawBlocks(blocks: Blocks) {
        println("지뢰찾기 게임 시작")
        val blockList = blocks.blockBoard.values.map { it }
        blockList.forEach { rowBlocks ->
            drawRowBlocks(rowBlocks, blocks)
        }
    }

    private fun drawRowBlocks(rowBlocks: List<Block>, blocks: Blocks) {
        println(rowBlocks.joinToString(" ") { getDrawBlockText(it, blocks) })
    }

    private fun getDrawBlockText(block: Block, blocks: Blocks): String {
        return when (block) {
            is Block.LandMine -> "*"
            is Block.Normal -> blocks.getAroundMineCount(block).toString()
        }
    }
}
