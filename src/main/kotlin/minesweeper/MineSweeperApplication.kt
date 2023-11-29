package minesweeper

import minesweeper.domain.RandomMineGenerator
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val height = InputView.inputHeight().value
    val width = InputView.inputWidth().value
    val count = InputView.inputCount().value

    val mines = RandomMineGenerator(height, width).generate(count)
    ResultView.printMines(height, width, mines)
}
