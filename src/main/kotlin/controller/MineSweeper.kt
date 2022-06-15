package controller

import domain.field.FieldInfo
import domain.field.Field
import view.InputView
import view.OutputView

class MineSweeper {
    fun start() {
        val fieldHeight = InputView.inputHeight()
        val fieldWidth = InputView.inputWidth()
        val mineCount = InputView.inputMineCount()

        val fieldInfo = FieldInfo(fieldHeight, fieldWidth, mineCount)
        val mineField = Field().buildField(fieldInfo)
        OutputView.printMineField(mineField)
    }
}

fun main(){
    val a = MineSweeper().start()
}

