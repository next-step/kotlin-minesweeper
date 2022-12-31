package minesweeper

import minesweeper.domain.BlockRow

object ResultView {
    fun printBlocks(blocks: List<BlockRow>) {
        println()
        println("지뢰찾기 게임 시작")
        blocks.forEach { println(it) }
    }
}
