package minesweeper

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView
import kotlin.system.exitProcess

fun main() {
    try {
        startGame()
    } catch (e: Exception) {
        println(e.message)
        exitProcess(0)
    }
}

fun startGame() {
    val length = InputView.inputLength()
    val width = InputView.inputWidth()
    val mineCount = InputView.inputMines()
    val board = Board(width, length, mineCount)
    ResultView.resultBoard(board, width - 1)
}
