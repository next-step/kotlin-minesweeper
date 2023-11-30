package minesweeper

import minesweeper.domain.RandomPositionGenerator
import minesweeper.ui.InputView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val count = InputView.inputCount()

    val positions = RandomPositionGenerator(height, width).generate(count)
//    ResultView.printMines(height, width, mines)
}
