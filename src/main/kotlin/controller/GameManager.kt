package controller

import domain.Field
import domain.RandomPositionSelector
import view.inputHeight
import view.inputMineNum
import view.inputOpenPosition
import view.inputWidth
import view.printBoard
import view.printStartMessage

object GameManager {
    fun prepareField() = Field(inputWidth(), inputHeight())

    fun setCells(field: Field) {
        val mineNum = inputMineNum()
        val selector = RandomPositionSelector(width = field.width, height = field.height)

        repeat(mineNum) {
            field.setMine(selector)
        }

        field.setHints()
    }

    fun runGame(field: Field) {
        printStartMessage()
        field.status = field.status.next()

        while (!field.isFinished()) {
            val (x, y) = inputOpenPosition()
            field.clickCell(x, y)
            printBoard(field.cells)
        }
    }
}
