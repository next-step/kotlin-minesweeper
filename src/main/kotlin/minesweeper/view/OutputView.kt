package minesweeper.view

import minesweeper.domain.MineSweeperIndex

object OutputView {

    fun printMineSweeperStart() {
        println(MINESWEEPER_START_MESSAGE)
    }
    fun printMineSweeper(chunked: Int, result: List<Int>) {
        result.chunked(chunked).forEach { it.printMineSweeperRow() }
    }

    private fun List<Int>.printMineSweeperRow() {
        val convert = this.map { if (it == MineSweeperIndex.MINE) MINE else it }
        println(convert.joinToString(" "))
    }

    private const val MINESWEEPER_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE = "*"
}
