package controller

import view.InputView

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
}
