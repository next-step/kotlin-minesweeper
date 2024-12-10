package minesweeper.app

import minesweeper.entity.MineField
import minesweeper.view.InputView
import minesweeper.view.OutputView

class Minesweeper {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun setUp(): MineField {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()
        return MineField.create(height, width, mineCount)
    }

    fun gameStart(mineField: MineField) {
        outputView.printGameStart()
        outputView.printMineField(mineField)
    }
}
