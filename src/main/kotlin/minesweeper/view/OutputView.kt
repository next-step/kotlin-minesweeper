package minesweeper.view

import minesweeper.domain.IndexResult
import minesweeper.domain.PositionStatus

object OutputView {

    fun printMineSweeperStart() {
        println(MINESWEEPER_START_MESSAGE)
    }

    fun printMineSweeper(chunked: Int, result: List<IndexResult>) {
        result.chunked(chunked).forEach { it.printMineSweeperRow() }
    }

    fun printLoseMineSweeper(chunked: Int, result: List<IndexResult>) {
        println(LOSE_MESSAGE)
        result.chunked(chunked).forEach { it.printLoseMineSweeperRow() }
    }

    private fun List<IndexResult>.printMineSweeperRow() {
        val convert = this.map { if (it.isOpened == PositionStatus.OPENED) it.mineCount.toString() else CLOSED }
        println(convert.joinToString(" "))
    }

    private fun List<IndexResult>.printLoseMineSweeperRow() {
        val convert = this.map { if (it.isMine) MINE else it.mineCount.toString() }
        println(convert.joinToString(" "))
    }

    private const val MINESWEEPER_START_MESSAGE = "지뢰찾기 게임 시작"
    private const val MINE = "*"
    private const val CLOSED = "C"
    private const val LOSE_MESSAGE = "Lose Game."
}
