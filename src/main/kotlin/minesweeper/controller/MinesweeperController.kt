package minesweeper.controller

import minesweeper.view.InputView
import minesweeper.view.OutputView

class MinesweeperController {
    fun start() {
        val boardSetting = InputView.readBoardSetting()
        val board = boardSetting.toBoard()

        OutputView.printGameStartMessage()
        OutputView.printBoard(board)
    }
}
