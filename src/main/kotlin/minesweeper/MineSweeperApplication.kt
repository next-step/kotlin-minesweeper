package minesweeper

import minesweeper.domain.CellFinder
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
    val cellFinder = CellFinder(initPosition)
    cellFinder.convert(minePositions)

    ResultView.printMines(height, width, cellFinder)
}
