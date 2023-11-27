package minesweeper

import minesweeper.domain.RandomMineGenerator
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val count = InputView.inputCount()

    val mines = RandomMineGenerator(height, width).generate(count)
    ResultView.printMines(height, width, mines)
}
