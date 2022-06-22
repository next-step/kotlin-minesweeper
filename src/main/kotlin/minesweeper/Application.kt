package minesweeper

import minesweeper.application.MineSweeper
import minesweeper.domain.field.Mine
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    val inputHeight = InputView.inputHeight()
    val inputWidth = InputView.inputWidth()
    val inputNumberOfMine = InputView.inputNumberOfMine()

    val mineSweeper = MineSweeper()
    val mineField = mineSweeper.createMineField(inputHeight, inputWidth, inputNumberOfMine)

    while (true) {
        val dot = mineSweeper.open(mineField, InputView.inputCoordinate())
        when {
            dot is Mine -> {
                OutputView.printGameOver()
                return
            }
            mineField.isAllOpen -> {
                OutputView.printGameEnd()
                return
            }
            else -> OutputView.printMineField(mineSweeper.createMineFieldView(mineField, inputWidth))
        }
    }
}
