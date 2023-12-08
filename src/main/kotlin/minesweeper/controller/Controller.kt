package minesweeper.controller

import minesweeper.view.InputView
import minesweeper.view.ResultView

object Controller {
    fun start() {
        val board = InputView.getBoard()
        ResultView.drawBoard(board)
    }
}
