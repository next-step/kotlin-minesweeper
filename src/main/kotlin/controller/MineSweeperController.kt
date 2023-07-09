package controller

import domain.MineSweeperBoard
import view.InputView
import view.ResultView

class MineSweeperController {
    fun inputHeight(): Int {
        return InputView.height()
    }

    fun inputWidth(): Int {
        return InputView.width()
    }

    fun inputMines(): Int {
        return InputView.mines()
    }

    fun printStartMessage() {
        ResultView.startMessage()
    }

    fun printBoard(board: MineSweeperBoard) {
        ResultView.board(board)
    }
}
