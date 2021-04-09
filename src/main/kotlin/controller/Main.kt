package controller

import model.board.BoardFactory
import model.board.BoardSize
import model.RandomPositionsBuilder
import view.InputView
import view.OutputView

fun main() {
    val boardSize = BoardSize(InputView.readHeight(), InputView.readWidth())
    val mineCount = InputView.readMineCount()

    val board = BoardFactory().create(boardSize, RandomPositionsBuilder().build(boardSize, mineCount))

    OutputView.printStart()
    OutputView.printBoard(board)
}
