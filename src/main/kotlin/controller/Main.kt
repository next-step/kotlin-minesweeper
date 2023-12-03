package controller

import domain.Field
import domain.RandomPositionSelector
import view.inputHeight
import view.inputMineNum
import view.inputOpenPosition
import view.inputWidth
import view.printBoard
import view.printResult
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

    var stillAlive = true
    while (field.mineRemains() && stillAlive) {
        val (x, y) = inputOpenPosition()
        stillAlive = field.clickCell(x, y)
        printBoard(field.cells)
    }

    printResult(stillAlive)
}
