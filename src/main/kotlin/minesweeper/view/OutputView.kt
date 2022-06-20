package minesweeper.view

import minesweeper.domain.Cell
import minesweeper.domain.Cells

object OutputView {

    fun printGameStart() {
        println("지뢰찾기 게임 시작")
    }

    fun printGameBoard(cells: Cells) {
        cells.groupByPositionX().forEach {
            println(it.joinToString(" ") { cell -> cell.toPoint() })
        }
    }

    fun printLose() {
        println("Lose Game.")
    }

    fun printWin() {
        println("Win Game.")
    }

    private fun Cell.toPoint(): String {
        return when (cellState.isOpen) {
            true -> cellState.mineCount.toString()
            false -> "C"
        }
    }
}
