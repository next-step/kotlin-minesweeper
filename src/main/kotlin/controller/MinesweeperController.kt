package controller

import view.InputView
import view.OutputView

class MinesweeperController {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()
    }
}

fun main() {
    MinesweeperController().run()
}
