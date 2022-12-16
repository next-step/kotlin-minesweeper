package controller

import domain.MineFieldFactory
import domain.Rectangle
import view.InputView
import view.OutputView

class MinesweeperController {
    private val inputView = InputView
    private val outputView = OutputView

    fun run() {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val rectangle = Rectangle(height, width)
        val mineField = MineFieldFactory().generate(rectangle, mineCount)
        outputView.printGameStart(mineField)
    }
}

fun main() {
    MinesweeperController().run()
}
