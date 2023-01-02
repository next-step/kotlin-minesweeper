package minesweeper

import minesweeper.domain.BlockRow

object ResultView {

    fun printStartGame() {
        println()
        println("지뢰찾기 게임 시작")
    }

    fun printBlocks(blocks: List<BlockRow>) {
        blocks.forEach { println(it) }
        println()
    }

    fun printWin() {
        println("Win Game.")
    }

    fun printLose() {
        println("Lose Game.")
    }
}
