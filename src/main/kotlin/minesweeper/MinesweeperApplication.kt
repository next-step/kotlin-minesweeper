package minesweeper

import minesweeper.model.MineField
import minesweeper.model.RandomMineFieldGenerator
import minesweeper.view.InputView
import minesweeper.view.ResultView

object MinesweeperApplication {
    fun run(inputView: InputView, resultView: ResultView) {
        inputView.printHeightInputMessage()
        val height = inputView.inputHeight()

        inputView.printWidthInputMessage()
        val width = inputView.inputWidth()

        inputView.printMineCountInputMessage()
        val mineCount = inputView.inputMineCount()

        val mineField = MineField(RandomMineFieldGenerator.generate(width, height, mineCount))
        resultView.printMineField(mineField)
    }
}
