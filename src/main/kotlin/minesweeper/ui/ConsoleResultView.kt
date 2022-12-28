package minesweeper.ui

import minesweeper.domain.Block

class ConsoleResultView : ResultView {
    override fun drawBlocks(blocks: List<List<Block>>) {
        println("지뢰찾기 게임 시작")
        blocks.forEach { rowBlocks ->
            println(
                rowBlocks.joinToString {
                    when (it) {
                        is Block.LandMine -> "*"
                        is Block.Normal -> "C"
                    }
                }.replace(",", "")
            )
        }
    }
}
