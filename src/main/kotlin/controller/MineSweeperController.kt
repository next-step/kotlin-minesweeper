package controller

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
        return ResultView.startMessage()
    }
}
