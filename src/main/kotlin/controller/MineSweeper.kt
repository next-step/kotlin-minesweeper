package controller

import domain.MineField
import view.InputView
import view.OutputView

class MineSweeper {
    fun start() {
        val fieldHeight = InputView.inputHeight()
        val fieldWidth = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()

        val fieldInfo = MineField.FieldInfo(fieldHeight, fieldWidth, mineCount)
        val mineField = MineField().buildField(fieldInfo)
        OutputView.printMineField(mineField)
    }
}

