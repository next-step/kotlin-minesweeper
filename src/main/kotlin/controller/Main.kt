package controller

import view.inputHeight
import view.inputMineNum
import view.inputWidth
import view.printStartMessage

fun main() {
    val height = inputHeight()
    val width = inputWidth()
    val mineNum = inputMineNum()

    printStartMessage()
}
