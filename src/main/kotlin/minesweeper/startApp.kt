package minesweeper

import minesweeper.domain.BoardFactory
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
    val board = BoardFactory.makeBoard(width, length) { BoardFactory.makeMineCoordinates(it, mineCount) }
    ResultView.resultBoard(board, width - 1)
}
