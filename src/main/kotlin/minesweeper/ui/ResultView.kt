package minesweeper.ui

import minesweeper.domain.Block

class ResultView {
    fun printBlocks(blocks: List<List<Block>>) {
        println("지뢰찾기 게임 시작")
        blocks.forEach { rowBlocks ->
            println(rowBlocks.joinToString { it.blockText }.replace(",", ""))
        }
    }
}
