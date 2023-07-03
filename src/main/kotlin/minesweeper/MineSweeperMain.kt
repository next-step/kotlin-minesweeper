package minesweeper

import minesweeper.domain.board.BoardRange
import minesweeper.domain.board.MineSweeperBoard
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val height = inputView.readHeight()
    val width = inputView.readWidth()

    val boardRange = BoardRange(height = height, width = width)

    val mineQuantity = inputView.readMineQuantity()

    val board = MineSweeperBoard(boardRange = boardRange, mineQuantity = mineQuantity)

    resultView.printGameStartMessage()
    resultView.printBoard(board = board)
}
