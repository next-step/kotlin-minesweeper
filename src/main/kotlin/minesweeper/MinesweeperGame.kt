package minesweeper

import minesweeper.view.InputView
import minesweeper.view.OutputView

class MinesweeperGame {

    fun start() {
        val inputHeight = InputView.inputHeight()
        val inputWidth = InputView.inputWidth()
        val inputMineCount = InputView.inputMineCount()

        val minesweeperFrame = MinesweeperFrame(inputHeight, inputWidth, inputMineCount)

        OutputView.show(minesweeperFrame.minePanel)
    }
}
