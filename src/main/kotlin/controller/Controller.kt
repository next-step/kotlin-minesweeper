package controller

import domain.Board
import presentation.InputView
import presentation.ResultView

fun main() {
    val board = Board(
        height = InputView.getHeight(),
        width = InputView.getWidth(),
        mineCount = InputView.getMineCount(),
    )
    ResultView.printBoard(board)
}
