package minesweeper.ui

import minesweeper.domain.Block
import minesweeper.domain.Blocks

class ConsoleResultView : ResultView {
    override fun drawBlocks(blocks: Blocks) {
        println("지뢰찾기 게임 시작")
        blocks.blockBoard.forEach { rowBlocks ->
            println(
                rowBlocks.joinToString {
                    when (it) {
                        is Block.LandMine -> "*"
                        is Block.Normal -> blocks.getAroundMineCount(it).toString()
                    }
                }.replace(",", "")
            )
        }
    }
}
