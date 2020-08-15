package minesweeper

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    try {
        startGame()
    } catch (e: Exception) {
        println(e.message)
    }
}

fun startGame() {
    val length = InputView.inputLength()
    val width = InputView.inputWidth()
    val board = Board(width, length)
    board.makePoint(InputView.inputMines())
    ResultView.resultBoard(board, width - 1)
}
