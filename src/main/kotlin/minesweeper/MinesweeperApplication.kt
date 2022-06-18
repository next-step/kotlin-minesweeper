package minesweeper

import minesweeper.model.MineBoard
import minesweeper.model.RandomMineBoardGenerator
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

        val mineBoard = MineBoard(RandomMineBoardGenerator.generate(width, height, mineCount))
        resultView.printMineBoard(mineBoard)
    }
}
