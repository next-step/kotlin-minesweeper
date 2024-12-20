package minesweeper

import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val dimensions = Dimensions(inputView.readWidth(), inputView.readHeight())
    val board = Board(dimensions, MinePlacer(dimensions, inputView.readMineCount()).placeMines())

    resultView.startView()
    board.open(Position.create(inputView.readOpenPosition()))
    resultView.drawBoard(board.draw())
}
