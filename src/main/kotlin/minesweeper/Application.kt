package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val dimensions = Dimensions(inputView.readWidth(), inputView.readHeight(), inputView.readMineCount())
    val board = Board.initializeBoard(dimensions, DefaultCellProvider())

    resultView.startView()
    board.detectMines()
    resultView.drawBoard(board.draw())
}
