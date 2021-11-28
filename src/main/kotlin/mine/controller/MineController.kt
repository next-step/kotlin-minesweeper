package mine.controller

import mine.view.InputView

class MineController {

    fun createGame() {
        val height = InputView.inputHeight()
        val width = InputView.inputWidth()
        val mine = InputView.inputMineCount()
    }
}
