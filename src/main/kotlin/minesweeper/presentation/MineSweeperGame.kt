package minesweeper.presentation

import minesweeper.domain.Board

object MineSweeperGame {
    fun start() {
        val width = InputView.inputWidth()
        val height = InputView.inputHeight()
        val mineCount = InputView.inputMineCount(width, height)

        val board = Board(width, height)
        board.placeMines(mineCount)

        ResultView.printBoard(board)
    }
}
