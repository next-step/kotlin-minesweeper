package minesweeper.controller

import minesweeper.domain.Board
import minesweeper.view.InputView
import minesweeper.view.ResultView

object MineSweeperGame {
    fun startGame() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mineCount = InputView.inputMines()
        val board = Board(width.number, height.number)
        board.makeCell(mineCount)
        ResultView.printBoard(board, width.number - 1)
    }
}
