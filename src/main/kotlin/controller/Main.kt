package controller

import domain.Field
import domain.RandomPositionSelector
import view.inputHeight
import view.inputMineNum
import view.inputWidth
import view.printBoard
import view.printStartMessage

fun main() {
    val height = inputHeight()
    val width = inputWidth()
    val mineNum = inputMineNum()

    val field = Field(width = width, height = height)

    val selector = RandomPositionSelector(width = width, height = height)
    repeat(mineNum) {
        field.setMine(selector)
    }

    field.setHints()

    printStartMessage()
    printBoard(field.cells)
}
