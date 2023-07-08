package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.Cells
import minesweeper.domain.Mine
import minesweeper.domain.Normal

object OutputView {
    private val NOT_OPENED = "."

    fun printGameStartMessage() {
        println("지뢰찾기 게임 시작")
    }

    fun printBoard(board: Board) {
        board.cells.forEach { row ->
            printCells(row)
            println()
        }
    }

    private fun printCells(cells: Cells) {
        cells.forEach { cell ->
            print("${cell.shape()} ")
        }
    }

    private fun Cell.shape(): String {
        return when (this) {
            is Mine -> NOT_OPENED
            is Normal -> shape()
        }
    }

    private fun Normal.shape(): String {
        if (isOpened) {
            return adjacentMineCount.toString()
        }
        return NOT_OPENED
    }

    fun printWinMessage() {
        println("Win Game.")
    }

    fun printLoseMessage() {
        println("Lose Game.")
    }
}
