package minesweeper

import minesweeper.domain.MineFinder
import minesweeper.domain.RandomPositionGenerator
import minesweeper.ui.InputView
import minesweeper.ui.ResultView

fun main() {
    val height = InputView.inputHeight()
    val width = InputView.inputWidth()
    val count = InputView.inputCount()

    val positionGenerator = RandomPositionGenerator(height, width)
    val initPosition = positionGenerator.init()
    val minePositions = positionGenerator.generate(count)
    val mineFinder = MineFinder.from(initPosition)
    mineFinder.convert(minePositions)

    ResultView.printMines(height, width, mineFinder)
}
