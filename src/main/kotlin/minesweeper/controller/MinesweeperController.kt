package minesweeper.controller

import minesweeper.view.InputView
import minesweeper.view.OutputView

class MinesweeperController {
    fun start() {
        val boardSetting = InputView.readBoardSetting()
        val board = boardSetting.toBoard()

        OutputView.printGameStartMessage()
        while (board.isNotGameOver()) {
            board.open(InputView.readPositionToOpen())
            OutputView.printBoard(board)
        }

        if (board.isWin()) {
            OutputView.printWinMessage()
            return
        }
        OutputView.printLoseMessage()
    }
}
