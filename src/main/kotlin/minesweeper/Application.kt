package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val dimensions = Dimensions(inputView.readWidth(), inputView.readHeight())
    val mineCount = inputView.readMineCount()

    val board = Board.initializeBoard(dimensions, mineCount, DefaultPositionProvider())

    resultView.startView()
    resultView.drawBoard(board.draw())
}