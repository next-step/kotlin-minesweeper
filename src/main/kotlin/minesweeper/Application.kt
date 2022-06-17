package minesweeper

import minesweeper.application.MineSweeper
import minesweeper.ui.InputView
import minesweeper.ui.OutputView

fun main() {
    val inputHeight = InputView.inputHeight()
    val inputWidth = InputView.inputWidth()
    val inputNumberOfMine = InputView.inputNumberOfMine()

    val mineFieldView = MineSweeper().createMineField(inputHeight, inputWidth, inputNumberOfMine)
    OutputView.printMineField(mineFieldView)
}
