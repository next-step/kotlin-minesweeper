package minesweeper.view

import minesweeper.domain.IndexResult

object OutputView {

    fun printMineSweeperStart() {
        println(MINESWEEPER_START_MESSAGE)
    }
    fun printMineSweeper(chunked: Int, result: List<IndexResult>) {
        result.chunked(chunked).forEach { it.printMineSweeperRow() }
    }

    private fun List<IndexResult>.printMineSweeperRow() {
        val convert = this.map { if (it.isMine) MINE else it.mineCount.toString() }
        println(convert.joinToString(" "))
    }

    private const val MINESWEEPER_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE = "*"
}
